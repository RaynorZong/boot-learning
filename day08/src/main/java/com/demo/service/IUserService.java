package com.demo.service;

import com.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
public interface IUserService extends IService<User> {
    List<User> selectUserByName(String name);

}
