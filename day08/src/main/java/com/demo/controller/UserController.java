package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.entity.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    @Autowired
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
                .orderByDesc("name");
        IPage<User> p = new Page<User>(offset,limit);
        return userService.page(p,queryWrapper);
    }
}
