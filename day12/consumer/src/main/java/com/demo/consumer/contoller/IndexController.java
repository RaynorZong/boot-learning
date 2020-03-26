package com.demo.consumer.contoller;

import demo.entity.User;
import demo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/users")
    public List<User> get(){
        return userService.userList();
    }
}
