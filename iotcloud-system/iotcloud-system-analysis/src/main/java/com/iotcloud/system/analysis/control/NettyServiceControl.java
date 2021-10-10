package com.iotcloud.system.analysis.control;

import com.iotcloud.common.core.exception.ErrorType;
import com.iotcloud.common.core.exception.NettyServerException;
import com.iotcloud.system.analysis.mapper.DeviceProtocolMapper;
import com.iotcloud.system.analysis.nettyConfig.NettyServiceFactory;
import com.iotcloud.system.analysis.utils.JarloadClass;
import com.kexsci.iotcloud.protocol.protocol.DeviceProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class NettyServiceControl {


    @Resource
    NettyServiceFactory nettyServiceFactory;


    @Resource
    DeviceProtocolMapper deviceProtocolMapper;


    @GetMapping("start/{port}")
    public String  start(@PathVariable int port) throws ClassNotFoundException {
        if(!nettyServiceFactory.hasNettyService(port)){
            throw new NettyServerException(ErrorType.SERVER_START_ERROR);
        }
        DeviceProtocol deviceProtocol = deviceProtocolMapper.getDeviceProtocol(port);
        if(ObjectUtils.isEmpty(deviceProtocol)){
            throw new NettyServerException(ErrorType.SERVER_JAR_ERROR);
        }
        Class<?> unpackClass = null;
        try {
            unpackClass = Class.forName(deviceProtocol.getUnpackClass());
        } catch (ClassNotFoundException e) {
            JarloadClass.loadJar(deviceProtocol.getFilePath());
            //log.info("重新加载jar包");
            unpackClass = Class.forName(deviceProtocol.getUnpackClass());
        }
        Class<?> decodeClass = null;
        try {
            decodeClass = Class.forName(deviceProtocol.getUnpackClass());
        } catch (ClassNotFoundException e) {
            JarloadClass.loadJar(deviceProtocol.getFilePath());
           // log.info("重新加载jar包");
            decodeClass = Class.forName(deviceProtocol.getUnpackClass());
        }
        nettyServiceFactory.start(port,unpackClass,decodeClass);
        return "success";
    }

    @GetMapping("stop/{port}")
    public String  stop(@PathVariable int port){
        if(nettyServiceFactory.hasNettyService(port)){
            throw new NettyServerException(ErrorType.SERVER_STOP_ERROR);
        }
        nettyServiceFactory.stop(port);
        return "success";
    }


}
