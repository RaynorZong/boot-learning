package com.demo.service;

import com.demo.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> pageArticle();
    Article selectArticleByXlh(String xlh);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(String xlh);


}
