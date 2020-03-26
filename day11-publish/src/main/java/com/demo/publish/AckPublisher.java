package com.demo.publish;

import com.demo.constants.MqConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class AckPublisher implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate jsonRabbitTemplate;

    @PostConstruct
    public void init() {
        jsonRabbitTemplate.setReturnCallback(this);
        jsonRabbitTemplate.setConfirmCallback(this);
    }
    @Override
    //接收发送后确认信息
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("ack send succeed: " + correlationData);
        } else {
            System.out.println("ack send failed: " + correlationData + "|" + s);
        }
    }

    @Override
    //发送失败的回调
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("ack " + message + " 发送失败");
    }

    //一般的用法，推送消息
    public String publish(String ans) {
        String msg = "ack msg = " + ans;
        System.out.println("publish: " + msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        jsonRabbitTemplate.convertAndSend(MqConstants.exchange, MqConstants.routing, msg, correlationData);
        return msg;
    }
}
