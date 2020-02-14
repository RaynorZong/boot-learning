package com.demo.web;

import com.demo.domain.UserList;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log
public class DemoController {

    @GetMapping("/list")
    @ResponseBody
    public String getUser(UserList userList) {
        log.info(userList.toString());
        return userList.toString();
    }

}
