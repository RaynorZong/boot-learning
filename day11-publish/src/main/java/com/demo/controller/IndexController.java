package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.publish.PublishDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private PublishDemo publishDemo;

    @GetMapping("/publish")
    public String publish(){
        publishDemo.publish2mq("Raynor");
        return "ok";
    }

    @GetMapping("/publish2")
    public String publish2(){
        User user = new User();
        user.setName("Paul");
        user.setPassword("123");
        publishDemo.publish2mq(user);
        return "ok";
    }

    @GetMapping("/publish3")
    public String publish3(){
        User user = new User();
        user.setName("Paul");
        user.setPassword("123");
        publishDemo.publish2mq3(JSON.toJSONString(user));
        return "ok";
    }
}
