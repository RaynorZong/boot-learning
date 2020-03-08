package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */

public interface IUserService extends IService<User> {

    User getOne(Long id);

    User updateUser(User user);

    void delete(User user);
}
