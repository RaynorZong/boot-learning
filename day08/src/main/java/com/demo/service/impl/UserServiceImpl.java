package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }
}
