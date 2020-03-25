package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String index() {
        return "redirect:/test1";
    }


    @RequestMapping("/test1")
    public @ResponseBody String inde1(){
        return "sss";
    }
}
