package com.demo.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log
public class IndexController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/add")
    public String test1(){
        return "admin/add";
    }

    @GetMapping(value = "/index")
    public String test2(){
        return "admin/index";
    }

    @GetMapping(value = "/detail")
    public String test3(){
        return "admin/index";
    }

    @GetMapping(value = "/test")
    public @ResponseBody  String test3(String id){
        log.info(id);
        return id;
    }

}
