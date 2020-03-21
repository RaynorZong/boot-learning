package com.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String login(String username,String password);
    String logout(HttpServletRequest request);
}
