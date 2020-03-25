package com.demo;

import com.demo.constants.MqConstants;
import com.demo.entity.User;
import com.demo.publish.PublishDemo;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private PublishDemo publishDemo;

    @Test
    void aa(){
        publishDemo.publish2mq("2222");
    }

    @Resource
    private RabbitTemplate jacksonRabbitTemplate;
    @Test
    void bb(){
        User user = new User();
        user.setName("Paul");
        user.setPassword("123");
        jacksonRabbitTemplate.convertAndSend(MqConstants.exchange,MqConstants.routing, user);
    }
}
