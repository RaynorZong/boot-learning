package com.demo.publish;

import com.demo.constants.MqConstants;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PublishDemo {

    @Resource
    private RabbitTemplate jacksonRabbitTemplate;//实现自amqpTemplate,使用起来并无区别

    public String publish2mq(Object object) {
        //将msg发送给指定的exchange，并设置消息的路由键
        //发送的消息默认是持久化的
        jacksonRabbitTemplate.convertAndSend(MqConstants.exchange, MqConstants.routing, object);
        return "ok";
    }

    //非持久化的消息发送
    public String publish2mq3(String ans) {
        String msg = "Define msg = " + ans;
        jacksonRabbitTemplate.convertAndSend(MqConstants.exchange, MqConstants.routing, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("test", "测试");
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                return message;
            }
        });

        return msg;
    }
}
