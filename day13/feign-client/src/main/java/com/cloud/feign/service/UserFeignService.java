package com.cloud.feign.service;

import com.cloud.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "USER-SERVICE")
public interface UserFeignService {

    @GetMapping("/user/")//路径要为user-service中的全路径,否则feign.FeignException$NotFound
    public List<User> userList();

}
