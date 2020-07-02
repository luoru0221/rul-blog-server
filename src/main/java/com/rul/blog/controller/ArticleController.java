package com.rul.blog.controller;

import com.rul.blog.pojo.Article;
import com.rul.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getAllArticle")
    public List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    @RequestMapping("/getArticleByAuthor")
    public List<Article> getArticleByAuthor(Integer userId) {
        return articleService.getArticleByAuthor(userId);
    }

    @RequestMapping("/getArticleById")
    public Article getArticleById(Integer articleId) {
        return articleService.getArticleById(articleId);
    }

    @RequestMapping("/getArticleByTag")
    public List<Article> getArticleByTag(Integer tagId){
        return articleService.getArticleByTag(tagId);
    }

    @RequestMapping("/publishArticle")
    public Article publishArticle(@RequestBody Article article) {
        return articleService.addNewArticle(article);
    }

    @RequestMapping("/saveEditor")
    public Boolean saveEditor(@RequestBody Article article) {
        return articleService.editorArticle(article);
    }

    @RequestMapping("/deleteArticle")
    public Boolean deleteArticle(Integer articleId){
        return articleService.deleteArticle(articleId);
    }
}
