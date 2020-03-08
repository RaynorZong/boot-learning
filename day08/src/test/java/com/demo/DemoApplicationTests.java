package com.demo;

import com.demo.Utils.Utils;
import com.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Test
    void contextLoads() {
        User old = new User();
        old.setId(1L);
        old.setName("张三");
        old.setPassword("123");
        old.setTime(LocalDateTime.now());
        User now = new User();
        now.setId(1L);
        now.setName("李四");
        now.setPassword("456");
        now.setTime(LocalDateTime.now());

        Utils.record(old,now);
        log.info(now.toString());

    }



}
