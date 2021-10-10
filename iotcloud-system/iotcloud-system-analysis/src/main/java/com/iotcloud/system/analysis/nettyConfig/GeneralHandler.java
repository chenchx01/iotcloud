package com.iotcloud.system.analysis.nettyConfig;

import com.iotcloud.common.rabbitmq.config.RabbitMQ;
import com.iotcloud.system.analysis.utils.SpringContextUtil;
import com.kexsci.iotcloud.protocol.code.DeviceMessageCodec;
import com.kexsci.iotcloud.protocol.code.MessageDecodeContext;
import com.kexsci.iotcloud.protocol.message.DeviceReportMessage;
import com.kexsci.iotcloud.protocol.message.DeviceSenSorMessage;
import com.kexsci.iotcloud.protocol.message.DeviceUnpackMessage;
import com.kexsci.iotcloud.protocol.message.SensorMessage;
import com.kexsci.iotcloud.protocol.queue.DeviceQueue;
import com.kexsci.iotcloud.protocol.session.DeviceSession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GeneralHandler extends ChannelInboundHandlerAdapter {

    RabbitMQ rabbitmq = SpringContextUtil.getBean(RabbitMQ.class);

    static int  port;

    Class<?> aClass1 = null;

    public GeneralHandler(int port,Class<?> aClass1) {
        this.port = port;
        this.aClass1 = aClass1;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketaddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("客户端的IP为" + socketaddress.getHostString() + "连接到服务器，唯一标识:" + ctx.channel().id().asLongText());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final String sessionId = ctx.channel().id().asLongText();
        log.info("客户端断开与服务器的连接，唯一标识:" + sessionId);
        ctx.channel().close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DeviceUnpackMessage deviceUnpackMessage = (DeviceUnpackMessage) msg;
        MessageDecodeContext messageDecodeContext = new MessageDecodeContext();
        DeviceSession deviceSession = NettyConstant.deviceSessionMap.get(ctx.channel().id().asShortText());
        if(ObjectUtils.isEmpty(deviceSession)){
              deviceSession = new DeviceSession();
              deviceSession.setChannel(ctx.channel());
              deviceSession.setConnectTime(System.currentTimeMillis());
              List<SensorMessage> list = new ArrayList<>();
              SensorMessage sensorMessage = new SensorMessage();
              sensorMessage.setSenCode("123");
              SensorMessage sensorMessage1 = new SensorMessage();
              sensorMessage1.setSenCode("456");
              list.add(sensorMessage);
              list.add(sensorMessage1);
              DeviceSenSorMessage deviceSenSorMessage = new DeviceSenSorMessage();
              deviceSenSorMessage.setSensorMessages(list);
              deviceSession.setDeviceMessage(deviceSenSorMessage);
              deviceSession.setDeviceQueue(new DeviceQueue());
              NettyConstant.deviceSessionMap.put(ctx.channel().id().asShortText(),deviceSession);
        }
        messageDecodeContext.setDeviceSession(deviceSession);
        DeviceMessageCodec instance2 = (DeviceMessageCodec) aClass1.newInstance();
        List<DeviceReportMessage>  result =instance2.decode(messageDecodeContext,deviceUnpackMessage);
        for (DeviceReportMessage de: result) {
               if(ObjectUtils.isEmpty( deviceSession.getDeviceId())&&!ObjectUtils.isEmpty( de.getDeviceCode())){
                   deviceSession.setDeviceId(de.getDeviceCode());
               }
               if(de.getMessageType()==1){
                   System.out.println(de);
               }
            rabbitmq.sendMsg2_2(de);
        }
        ByteBuf encoded = ctx.alloc().buffer();
        encoded.writeBytes("success".getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    public static String byteToHex(byte[] bytes) {
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        if(ObjectUtils.isEmpty(bytes)){
            return "";
        }
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
            sb.append(" ");
        }
        return sb.toString().trim().toUpperCase();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("服务器主动断开连接", ctx.channel().id().asLongText());
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }


}
