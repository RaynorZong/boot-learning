package com.demo.mapper;

import com.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
@Mapper
public interface UserMapper  {

    @Cacheable(cacheNames = "user")
    @Select("select * from user where id = #{id}")
    User getUserById(Long id);

    @Delete("delete from user where id=#{id}")
    int deleteUserById(Long id);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into user (name,password) values(#{name},#{password})")
    int insertUser(User user);

    @Update("update user set name=#{name} where id = #{id}")
    int updateUser(User user);

}
