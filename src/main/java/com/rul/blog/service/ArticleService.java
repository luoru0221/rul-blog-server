package com.rul.blog.service;

import com.rul.blog.pojo.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticle();

    List<Article> getArticleByAuthor(Integer userId);

    List<Article> getArticleByTag(Integer tagId);

    Article getArticleById(Integer articleId);

    Article addNewArticle(Article article);

    boolean editorArticle(Article article);

    boolean deleteArticle(Integer articleId);

    Article recoverArticle(Integer articleId);

    void deleteBackup(Integer userId, Integer articleId);

    List<Article> getBackup(Integer userId);

}
