package com.demo.mapper;
import com.demo.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> selectPage();
    Article selectArticleByXlh(String xlh);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(String xlh);
}
