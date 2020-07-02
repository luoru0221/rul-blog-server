package com.rul.blog.mapper;

import com.rul.blog.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    void findAuthorIdByArticleIdTest() {
        System.out.println(articleMapper.findAuthorIdByArticleId(4));
    }

    @Test
    void findArticleByTagIdTest(){
        List<Article> articles = articleMapper.findArticleByTagId(1);
        System.out.println(articles);
    }
}
