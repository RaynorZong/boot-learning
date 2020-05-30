package com.cloud.feign.controller;

import com.cloud.demo.entity.User;
import com.cloud.feign.service.UserFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/feign")
public class UserFeignController {
    @Resource
    private UserFeignService userFeignService;

    @GetMapping("/")
    public List<User> userList(){
        return userFeignService.userList();
    }

}
