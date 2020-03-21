package com.demo.service;

public interface RedisService {
    void setToken(String key,Object value);
    Object getToken(String key);
    boolean deleteToken(String key);
}
