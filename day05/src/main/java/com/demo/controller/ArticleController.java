package com.demo.controller;

import com.demo.model.Article;
import com.demo.service.ArticleService;
import com.demo.utils.RestResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/{pageSize}/{pageNum}")
    public PageInfo<Article> pageArticle(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list= articleService.pageArticle();
        PageInfo<Article> page = new PageInfo<>(list);
        return page;
    }

    @PostMapping("/")
    public RestResult addArticle(@RequestBody Article article){
        article.setXlh(UUID.randomUUID().toString());
        article.setCreate_date(LocalDate.now());
        article.setCreate_time(LocalDateTime.now());
        articleService.addArticle(article);
        return new RestResult("1",article,"");
    }
    @GetMapping("/{xlh}")
    public Article oneArticle(@PathVariable String xlh){
        return articleService.selectArticleByXlh(xlh);
    }
    @PutMapping("/{xlh}")
    public RestResult updateArticle(@PathVariable String xlh,@RequestBody Article article){
        article.setXlh(xlh);
        articleService.updateArticle(article);
        return new RestResult("1","success","");
    }
    @DeleteMapping("/{xlh}")
    public RestResult deleteArticle(@PathVariable String xlh){
        articleService.deleteArticle(xlh);
        return new RestResult("1","success","");
    }
}
