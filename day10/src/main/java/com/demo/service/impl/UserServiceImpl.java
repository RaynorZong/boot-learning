package com.demo.service.impl;

import com.demo.service.RedisService;
import com.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RedisService redisService;

    @Override
    public String login(String username, String password) {
        if (Objects.equals("test", username) && Objects.equals("123", password)) {
            String token = UUID.randomUUID().toString();
            redisService.setToken(token, username);
            return "用户：" + username + "登录成功，token是：" + token;
        } else {
            return "用户名或密码错误，登录失败！";
        }

    }

    @Override
    public String logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        Boolean delete = redisService.deleteToken(token);
        if (!delete) {
            return "注销失败，请检查是否登录！";
        }
        return "注销成功！";
    }
}
