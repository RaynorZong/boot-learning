package com.cloud.demo.controller;


import com.cloud.demo.entity.User;
import com.cloud.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/")
    public List<User> userList(){
        return userService.list();
    }

    @GetMapping("/{id}")
    public User userOne(@PathVariable Long id ){

        log.info("user-service-userOne"+id);
        return userService.getById(id);
    }

    @PutMapping("/")
    public User userUpdate(@RequestBody  User user){
        userService.updateById(user);
        return user;
    }
}
