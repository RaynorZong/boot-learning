package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserByName(String name);
}
