package com.demo03.web;

import com.demo03.config.ErrorMessage;
import com.demo03.domain.User;
import com.demo03.exception.LogicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "/index.html";
    }

    @GetMapping ("/get")
    public @ResponseBody User getUser(){
        User u = new User();
        u.setName("x");
        u.setTime(LocalDateTime.now());
        return u;
    }

    @PostMapping("/map")
    public @ResponseBody User getMap(@RequestBody Map<String,Object> map){
        User u = new User();
        u.setName("x");
        u.setTime(LocalDateTime.now());
        return u;
    }
    @PostMapping("/map1")
    public @ResponseBody User getMap1(@RequestParam Map<String,Object> map){
        User u = new User();
        u.setName("x");
        u.setTime(LocalDateTime.now());
        return u;
    }
    /**
     * 业务逻辑异常
     */
    @GetMapping(path = "logicException")
    public void logicException() {
        throw LogicException.getLogicException(ErrorMessage.LOGIC_EXCEPTION.msg());
    }

    /**
     * 系统异常
     */
    @GetMapping(path = "systemException")
    public void systemException() {
        throw new NullPointerException("空指针了");
    }
}
