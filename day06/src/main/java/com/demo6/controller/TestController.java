package com.demo6.controller;

import com.demo6.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class TestController {

    @PostMapping(value ="/json", consumes={MediaType.APPLICATION_JSON_UTF8_VALUE }, produces="application/json;charset=UTF-8")
    @ResponseBody
    public User json(@RequestBody Map<String,Object> jsonData){
        Map<String,Object> resultData = new HashMap<>(jsonData);
        resultData.put("resultCode", UUID.randomUUID().toString());
        User user = new User();
        user.setDate(LocalDate.now());
        return user;
    }

    @PostMapping(value = "xml",consumes = {MediaType.APPLICATION_XML_VALUE},produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User xml(@RequestBody User user){
      log.info(user.getName());
      return user;
    }
}
