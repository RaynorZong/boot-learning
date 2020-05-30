package com.cloud.demo.controller;

import com.cloud.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hystrix/user/{id}")
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "fallbackMethod1")
    public User getUser(@PathVariable Long id){
        return restTemplate.getForObject("http://user-service/user/"+id,User.class);
}

    @PutMapping("/hystrix/putUser")
    @HystrixCommand
    @CacheRemove(cacheKeyMethod = "getCacheKey1",commandKey = "getUser")
    public User user(@RequestBody User user){
        restTemplate.put("http://user-service/user/",user,user.getId());
        return user;
    }


    public User fallbackMethod1(@PathVariable Long id){
        System.out.println("服务调用失败");
        return new User();
    }


    @GetMapping("/hystrix/exception/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod1",
            ignoreExceptions = NullPointerException.class
    )
    public User exceptionTest(@PathVariable Long id){
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return new User();
    }
    @GetMapping("/hystrix/exception1/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod1",
            ignoreExceptions = NullPointerException.class,
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool"
    )
    public User exceptionTest1(@PathVariable Long id){
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return new User();
    }


    /**
     * 为缓存生成key的方法
     *
     * @return
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }
    public String getCacheKey1(User user) {
        return String.valueOf(user.getId());
    }


    @GetMapping("/hystrixCollapser/user/")
    public List<User> collapserTest() throws ExecutionException, InterruptedException {
        Future<User> future1 = getSinglePerson(1L);
        Future<User> future2 = getSinglePerson(2L);
        User user1 = future1.get();
        User user2 = future2.get();
        Thread.sleep(300);
        Future<User> future3 = getSinglePerson(3L);
        User user3 = future3.get();
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;

    }



    // 配置收集1秒内的请求
    @HystrixCollapser(batchMethod = "getUsers",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = { @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")}
    )
    public Future<User> getSinglePerson(Long id) {
        System.out.println("执行单个获取的方法");
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://user-service/user/"+id,User.class);
            }
            @Override
            public User get() {
                return invoke();
            }
        };
    }

    @HystrixCommand
    public List<User> getUsers(List<Long> ids) {
        System.out.println("收集请求，参数数量：" + ids.size());
        List<User> list = new ArrayList<User>();
        for (Long id : ids) {
            User user = restTemplate.getForObject("http://user-service/user/"+id,User.class);
        }
        return list;
    }
}
