package com.demo.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

     @GetMapping("/vis")
    public String vis(){
         return "/templates/neovis/vis.html";
     }

    @GetMapping("/d3")
    public String d3(){
        return "/templates/d3/index.html";
    }
}
