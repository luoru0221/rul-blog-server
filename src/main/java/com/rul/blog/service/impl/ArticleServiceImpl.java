package com.rul.blog.service.impl;

import com.rul.blog.mapper.ArticleMapper;
import com.rul.blog.pojo.Article;
import com.rul.blog.service.ArticleService;
import com.rul.blog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.findAllArticle();
    }

    @Override
    public Article getArticleById(Integer articleId) {
        //先从redis缓存中读取
        if (redisUtil.hasKey("article:"+articleId)){
            return (Article)redisUtil.get("article:"+articleId);
        }
        Article article = articleMapper.findArticleById(articleId);
        //将数据写入缓存并设置有效时间为1h
        redisUtil.set("article:"+articleId,article,3600);
        return article;
    }
}
