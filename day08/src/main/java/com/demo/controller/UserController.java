package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.Utils.Utils;
import com.demo.entity.User;
import com.demo.service.IUserService;
import com.demo.task.TestTaskAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{limit}/{offset}/")
    public IPage<User> userPage(@PathVariable Long limit,@PathVariable Long offset){
        IPage<User> p = new Page<User>(offset,limit);
        return userService.page(p);
    }
    @PostMapping("/{limit}/{offset}/")
    public IPage<User> userPage(@PathVariable Long limit, @PathVariable Long offset, @RequestBody User user){
        QueryWrapper<User> queryWrapper =new QueryWrapper();
        queryWrapper.eq("name",user.getName())
                .eq("password",user.getPassword())
                .orderByDesc("time");
        IPage<User> p = new Page<User>(offset,limit);
        return userService.page(p,queryWrapper);
    }

    @PostMapping("/query")
    public List<User> userList(@RequestParam String name){
        return userService.selectUserByName(name);
    }

    @Resource
    private TestTaskAsync testTaskAsync;

    @GetMapping("/async")
    public String async(){
        log.info("11111");
        testTaskAsync.test();
        return "ok";
    }
    @GetMapping("/async1")
    public String async1() throws InterruptedException {
        Future<String> future = testTaskAsync.get();
        while(true){
            if(future.isDone()){
                log.info("is done");
                break;
            }
            Thread.sleep(200);
        }
        return "ok";
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id,@RequestBody User user){
        User old = userService.getById(id);
        Utils.record(old,user);
        userService.saveOrUpdate(user);//更新操作
        return user;
    }
}
