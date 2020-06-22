package com.rul.blog.service;

import com.rul.blog.pojo.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticle();

    Article getArticleById(Integer articleId);

}
