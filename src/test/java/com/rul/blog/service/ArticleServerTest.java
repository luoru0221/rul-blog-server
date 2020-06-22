package com.rul.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServerTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void getAllArticleTest(){
        System.out.println(articleService.getAllArticle());
    }

    @Test
    void getArticleById(){
        System.out.println(articleService.getArticleById(1));
    }
}
