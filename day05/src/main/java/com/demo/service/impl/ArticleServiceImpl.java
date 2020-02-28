package com.demo.service.impl;

import com.demo.mapper.ArticleMapper;
import com.demo.model.Article;
import com.demo.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper mapper;


    @Override
    public List<Article> pageArticle() {
        return mapper.selectPage();
    }

    @Override
    public Article selectArticleByXlh(String xlh) {
        return mapper.selectArticleByXlh(xlh);
    }

    @Override
    public void addArticle(Article article) {
        mapper.addArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        mapper.updateArticle(article);
    }

    @Override
    public void deleteArticle(String xlh) {
        mapper.deleteArticle(xlh);
    }
}
