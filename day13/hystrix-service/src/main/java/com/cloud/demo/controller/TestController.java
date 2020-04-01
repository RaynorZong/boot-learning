package com.cloud.demo.controller;

import com.cloud.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hystrix/user/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod1")
    public User getUser(@PathVariable Long id){
        return restTemplate.getForObject("http://localhost:8010/user/"+id,User.class);
    }

    public User fallbackMethod1(@PathVariable Long id){
        System.out.println("服务调用失败");
        return new User();
    }


    @GetMapping("/hystrix/exception/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod1",
            ignoreExceptions = NullPointerException.class
    )
    public User exceptionTest(@PathVariable Long id){
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return new User();
    }
    @GetMapping("/hystrix/exception/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod1",
            ignoreExceptions = NullPointerException.class,
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool"
    )
    public User exceptionTest1(@PathVariable Long id){
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return new User();
    }
}
