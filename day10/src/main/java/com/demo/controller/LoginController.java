package com.demo.controller;

import com.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login(String username,String password){
        return userService.login(username,password);
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        return userService.logout(request);
    }
    @GetMapping("/test")
    public String test(HttpServletRequest request){
        return "ok";
    }
}
