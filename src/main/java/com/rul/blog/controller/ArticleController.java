package com.rul.blog.controller;

import com.rul.blog.pojo.Article;
import com.rul.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @RequestMapping("/getArticleById")
    public Article getArticleById(Integer articleId){
        return articleService.getArticleById(articleId);
    }


}
