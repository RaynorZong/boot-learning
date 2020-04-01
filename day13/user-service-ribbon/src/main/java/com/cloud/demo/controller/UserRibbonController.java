package com.cloud.demo.controller;

import com.cloud.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController

public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User userList(@PathVariable Long id ){
       User user = restTemplate.getForObject("http://user-service/user/"+id,User.class);
       return user;
    }
}
