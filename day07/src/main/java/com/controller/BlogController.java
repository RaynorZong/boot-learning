package com.controller;

import com.entity.Blog;
import com.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogRepository blogRepository;

    @GetMapping("/{pageNum}/{pageSize}/{username}")
    public Page<Blog> getBlogByUsername(@PathVariable Integer pageNum,
        @PathVariable Integer pageSize, @PathVariable String username){
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        return blogRepository.findByUsername(username,pageRequest);
    }
    @PostMapping("/")
    public String addBlog(@RequestBody Blog blog){
        blogRepository.save(blog);
        return "ok";
    }


}
