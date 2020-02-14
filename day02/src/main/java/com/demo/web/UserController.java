package com.demo.web;

import com.demo.domain.User;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/")//get请求:用户列表
    public List<User> listUsers() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setName("1");
        user.setTime(LocalDateTime.now());
        list.add(user);
        return list;
    }

    @PostMapping("/")// post请求:新增用户
    public String postUser(@RequestBody(required = false) User user) {
        log.info(user + "...");
        return "success";
    }

    @GetMapping("/{id}")// get请求:根据id获取用户
    public User getUser(@PathVariable Long id) {
        log.info(id + "..");
        return new User();
    }

    @PutMapping("/{id}")// put请求:根据id,user更新用户
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "success";
    }
}
