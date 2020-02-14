package com.demo03.web;

import com.demo03.config.ErrorMessage;
import com.demo03.domain.User;
import com.demo03.exception.LogicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

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
    /**
     * 业务逻辑异常
     */
    @GetMapping(path = "logicException")
    public void logicException() {
        throw LogicException.le(ErrorMessage.LOGIC_EXCEPTION.msg());
    }

    /**
     * 系统异常
     */
    @GetMapping(path = "systemException")
    public void systemException() {
        throw new NullPointerException("空指针了");
    }
}
