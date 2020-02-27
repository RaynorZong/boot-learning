package com.controller;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/")
    public Page<User> getUser(){
        PageRequest pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "name");
        PageRequest pageRequest = PageRequest.of(1,2);
        Page<User> page = userRepository.findAll(pageRequest);
        return page;
    }
    @PostMapping("/")
    public String addUser(@RequestBody  User user){
        userRepository.save(user);
        return "ok";
    }
}
