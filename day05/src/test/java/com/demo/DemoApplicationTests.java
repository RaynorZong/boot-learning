package com.demo;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.getUserById(1L);
        log.info(user.toString());
        user.setName("xxx1");
        user.setId(10L);
        int update = userMapper.updateUser(user);
        log.info("成功返回1,失败返回0:"+update);
        User user1 = new User();
        user1.setName("Eric");
        user1.setPassword("123456");
        int id = userMapper.insertUser(user);
        log.info("插入数据的主键:"+user.getId()+"");
        int del = userMapper.deleteUserById(user.getId());
        log.info("成功返回1,失败返回0:"+del);
    }
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1L);
        log.info(user.toString());
        user.setPassword("123");
        userMapper.updateUser(user);
        log.info(userMapper.getUserById(1L).toString());
    }

    @Test
    void test1(){
        try {

            SqlSession sqlSession1 = sqlSessionFactory.openSession(false);
            UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

            SqlSession sqlSession2 = sqlSessionFactory.openSession(false);
            UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

            User user = userMapper1.getUserById(1L);
            log.info(user.toString());

            user.setPassword("123");
            userMapper2.updateUser(user);
            //User user1 = new User();
            //userMapper2.updateUser(user1);
            //sqlSession2.commit();

            log.info(userMapper1.getUserById(1L).toString());
        }catch (Exception e){


        }

    }
}
