package com.iotcloud.common.rabbitmq.config;

import com.kexsci.iotcloud.protocol.message.DeviceReportMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class RabbitMQ {

    @Autowired
    RabbitTemplate rabbitTemplate;



    public String sendMsg2_2(DeviceReportMessage deviceReportMessage) {
        byte[] bytes = SerializationUtils.serialize(deviceReportMessage);
        rabbitTemplate.convertAndSend(RabbitMqOnlineConfig.EXCHANGE_WL2, RabbitMqOnlineConfig.ROUTING_KEY2,bytes);
        return "success";
    }



    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
