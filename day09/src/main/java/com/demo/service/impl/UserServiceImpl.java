package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.IUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(key = "#id")
    //如果在CacheConfig中指定了name,此处可以不用写value值
    //也可以用 “#p参数index”,此处为 #p0
    public User getOne(Long id) {
        return userMapper.selectById(id);
    }

    // 在更新数据的同时，缓存也被更新
    @Override
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        userMapper.updateById(user);
        return user;
    }
    ////清除一条缓存，key为要清空的数据
    @CacheEvict(key = "#p0.id")
    public void delete(User user){
    }

    //方法调用后清空所有缓存
    @CacheEvict(allEntries = true)
    public void deleteAll(){

    }
}
