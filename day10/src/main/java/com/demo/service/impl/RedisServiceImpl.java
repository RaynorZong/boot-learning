package com.demo.service.impl;

import com.demo.service.RedisService;
import com.demo.util.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void setToken(String key, Object value) {
        redisUtils.set(key,value);
    }

    @Override
    public Object getToken(String key) {
        return redisUtils.get(key);
    }

    @Override
    public boolean deleteToken(String key) {
        redisUtils.del(key);
        return true;
    }
}
