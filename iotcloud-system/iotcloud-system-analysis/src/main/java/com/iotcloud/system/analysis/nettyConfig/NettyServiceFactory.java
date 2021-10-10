package com.iotcloud.system.analysis.nettyConfig;

import com.iotcloud.common.core.exception.ErrorType;
import com.iotcloud.common.core.exception.NettyServerException;
import com.iotcloud.system.analysis.mapper.DeviceProtocolMapper;
import com.iotcloud.system.analysis.utils.JarloadClass;
import com.kexsci.iotcloud.protocol.code.DeviceMessageUnpack;
import com.kexsci.iotcloud.protocol.protocol.DeviceProtocol;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyServiceFactory {

    @Resource
    DeviceProtocolMapper deviceProtocolMapper;

    public void getNettyService(int port,Class<?> unpack,Class<?> decode) throws IllegalAccessException, InstantiationException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup otherGroup = new NioEventLoopGroup();
        log.info("协议准备运行端口：" + port);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入心跳检测，超过10分钟没有接收到用户传来的信息，服务端主动断开连接
                            ch.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.MINUTES));
                            if( unpack.newInstance() != null){
                                ch.pipeline().addLast((DeviceMessageUnpack) unpack.newInstance());
                            }
                            ch.pipeline().addLast(otherGroup,new GeneralHandler(port,decode));
                        }
                    });
            //绑定端口，同步等待成功
            try {
                ChannelFuture f =  bootstrap.bind(port).sync();
                NettyConstant.nettySerVermap.put(String.valueOf(port),f);
                //等待服务监听端口关闭
                f.channel().closeFuture().sync();
            } catch (Exception e) {
               // e.printStackTrace();
                throw new NettyServerException(ErrorType.SERVER_RUN_ERROR);
            }
        } finally {
            //退出，释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void start(int port,Class<?> unpackClass,Class<?> decodeClass){
        if(!ObjectUtils.isEmpty(NettyConstant.nettySerVermap.get(String.valueOf(port)))){
            throw new NettyServerException(ErrorType.SERVER_START_ERROR);
        }
        Thread thread = new Thread(()->{
            NettyServiceFactory nettyServiceFactory = new NettyServiceFactory();
            try {
                nettyServiceFactory.getNettyService(port,unpackClass,decodeClass);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void stop(int port){
        ChannelFuture channelFuture = NettyConstant.nettySerVermap.get(String.valueOf(port));
        if(ObjectUtils.isEmpty(channelFuture)){
            throw new NettyServerException(ErrorType.SERVER_STOP_ERROR);
        }
        channelFuture.channel().close();
        NettyConstant.nettySerVermap.remove(String.valueOf(port));
    }

    public boolean hasNettyService(int port){
        ChannelFuture channelFuture = NettyConstant.nettySerVermap.get(String.valueOf(port));
        return ObjectUtils.isEmpty(channelFuture) ;
    }

    // @PostConstruct
    private void startTCP() throws ClassNotFoundException {
        int prot = 9632;
        DeviceProtocol deviceProtocol = deviceProtocolMapper.getDeviceProtocol(prot);
        Class<?> unpackClass = null;
        try {
            unpackClass = Class.forName(deviceProtocol.getUnpackClass());
        } catch (Exception e) {
            JarloadClass.loadJar(deviceProtocol.getFilePath());
            log.info("重新加载jar包");
            try{
                 unpackClass = Class.forName(deviceProtocol.getUnpackClass());
            } catch (Exception ex) {
                // ex.printStackTrace();
                throw  new NettyServerException(ErrorType.SERVER_JAR_ERROR);
            }
        }
        Class<?> decodeClass = null;
        try {
            decodeClass = Class.forName(deviceProtocol.getDecoderClass());
        } catch (Exception e) {
            JarloadClass.loadJar(deviceProtocol.getFilePath());
            log.info("重新加载jar包");
            try {
                decodeClass = Class.forName(deviceProtocol.getDecoderClass());
            } catch (Exception ex) {
               // ex.printStackTrace();
                throw  new NettyServerException(ErrorType.SERVER_JAR_ERROR);
            }
        }
          start(prot,unpackClass,decodeClass);
    }
}
