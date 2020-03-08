package com.demo;

import com.demo.entity.User;
import com.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.Resource;

@SpringBootTest
@EnableCaching
@Slf4j
class DemoApplicationTests {

    @Resource
    IUserService userService;
    @Test
    void contextLoads() {
        User user = userService.getOne(1L);
        user.setPassword("11111");
        userService.updateUser(user);
        log.info(userService.getOne(1L).toString());
        userService.delete(user);
        log.info(userService.getOne(1L).toString());
    }

}
