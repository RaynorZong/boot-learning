package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.publish.AckPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AckPublisherController {


    @Autowired
    private AckPublisher ackPublisher;

    @GetMapping("/ack/publish")
    public String publish3(){
        User user = new User();
        user.setName("Paul");
        user.setPassword("123");
        ackPublisher.publish(JSON.toJSONString(user));
        return "ok";
    }
}
