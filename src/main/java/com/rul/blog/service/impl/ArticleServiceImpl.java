package com.rul.blog.service.impl;

import com.rul.blog.mapper.ArticleMapper;
import com.rul.blog.mapper.TagMapper;
import com.rul.blog.pojo.Article;
import com.rul.blog.pojo.Tag;
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
    private TagMapper tagMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Article> getAllArticle() {
        return articleMapper.findAllArticle();
    }

    @Override
    public List<Article> getArticleByAuthor(Integer userId) {
        return articleMapper.findArticleByAuthor(userId);
    }

    @Override
    public List<Article> getArticleByTag(Integer tagId) {
        return articleMapper.findArticleByTagId(tagId);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        //先从redis缓存中读取
        if (redisUtil.hasKey("article:" + articleId)) {
            return (Article) redisUtil.get("article:" + articleId);
        }
        Article article = articleMapper.findArticleById(articleId);
        //将数据写入缓存并设置有效时间为1h
        redisUtil.set("article:" + articleId, article, 3600);
        return article;
    }

    @Override
    public Article addNewArticle(Article article) {
        //将文章信息加入到数据库
        articleMapper.addArticle(article);
        //添加将新标签到数据库
        addNewTags(article);
        //返回新文章
        return getArticleById(article.getArticleId());
    }

    @Override
    public boolean editorArticle(Article article) {
        Integer authorId = articleMapper.findAuthorIdByArticleId(article.getArticleId());
        if (!authorId.toString().equals(article.getArticleAuthor())) {
            //修改的不是自己的文章
            return false;
        }

        //更新文章标题和内容
        if (articleMapper.updateTitleAndContent(article) == 0) {
            return false;
        }
        //添加新标签
        addNewTags(article);
        tagMapper.deleteArticleTags(article.getArticleId(), article.getTags());

        //更新缓存
        Article latestArticle = articleMapper.findArticleById(article.getArticleId());
        redisUtil.set("article:" + latestArticle.getArticleId(), latestArticle, 3600);
        return true;
    }

    @Override
    public boolean deleteArticle(Integer articleId) {
        //删除文章
        Integer rows = articleMapper.deleteArticleById(articleId);
        //删除标签
        tagMapper.deleteArticleAllTags(articleId);
        return rows > 0;
    }

    public void addNewTags(Article article) {
        //文章的标签
        List<Tag> tags = article.getTags();

        if (tags != null) {
            //已经存在的标签
            List<Tag> existTags = tagMapper.findTagsByName(tags);
            //过滤掉已经存在的标签
            tags.removeIf(tag -> {
                for (Tag existTag : existTags) {
                    if (existTag.getTagName().equals(tag.getTagName())) {
                        return true;
                    }
                }
                return false;
            });

            if (tags != null && tags.size() > 0) {
                //将不存在的标签加入数据库
                tagMapper.addTagsByName(tags);
            }
            //合并标签
            tags.addAll(existTags);
            if (tags != null && tags.size() > 0) {
                //为文章绑定新标签
                tagMapper.addArticleTags(article.getArticleId(), tags);
            }
        }
    }
}
