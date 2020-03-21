package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class DemoApplication {
    @Resource
    ApplicationContext context;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



}
