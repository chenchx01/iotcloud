package com.iotcloud.common.rabbitmq.config;


import com.kexsci.iotcloud.protocol.message.DeviceReportMessage;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

//@Configuration
public class RabbitMQConfig {

    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(value = RabbitMqOnlineConfig.QUEUE1, durable = "true"),
            exchange = @Exchange(name = RabbitMqOnlineConfig.EXCHANGE_WL2, durable = "true", type = "direct"),
            key = RabbitMqOnlineConfig.ROUTING_KEY2
    ))
    @RabbitHandler
    public void onOrderMessage(byte[] bytes) {
        System.out.println("----------收到消息，开始消费-----------");
        DeviceReportMessage deviceReportMessage = (DeviceReportMessage) SerializationUtils.deserialize(bytes);
        System.out.println(deviceReportMessage);
        System.out.println("----------收到消息，开始结束-----------");
    }

}
