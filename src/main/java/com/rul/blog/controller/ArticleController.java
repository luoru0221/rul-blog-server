package com.rul.blog.controller;

import com.rul.blog.pojo.Article;
import com.rul.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章服务接口层
 *
 * @author RuL
 * @time 2020-06-27
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取所有文章
     *
     * @return 所有文章
     */
    @RequestMapping("/getAllArticle")
    public List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    /**
     * 根据作者ID获取文章
     *
     * @param userId 作者ID
     * @return 该作者所有文章
     */
    @RequestMapping("/getArticleByAuthor")
    public List<Article> getArticleByAuthor(Integer userId) {
        return articleService.getArticleByAuthor(userId);
    }

    /**
     * 根据文章ID获取文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    @RequestMapping("/getArticleById")
    public Article getArticleById(Integer articleId) {
        return articleService.getArticleById(articleId);
    }

    /**
     * 根据标签获取文章
     *
     * @param tagId 标签ID
     * @return 拥有该标签的所有文章
     */
    @RequestMapping("/getArticleByTag")
    public List<Article> getArticleByTag(Integer tagId) {
        return articleService.getArticleByTag(tagId);
    }

    /**
     * 发布文章
     *
     * @param article 待发布的文章
     * @return 发布成功后的文章
     */
    @RequestMapping("/publishArticle")
    public Article publishArticle(@RequestBody Article article) {
        return articleService.addNewArticle(article);
    }

    /**
     * 保存修改后的文章
     *
     * @param article 修改后的文章
     * @return 保存成功与否
     */
    @RequestMapping("/saveEditor")
    public Boolean saveEditor(@RequestBody Article article) {
        return articleService.editorArticle(article);
    }

    /**
     * 根据文章ID删除文章
     *
     * @param articleId 文章ID
     * @return 删除成功与否
     */
    @RequestMapping("/deleteArticle")
    public Boolean deleteArticle(Integer articleId) {
        return articleService.deleteArticle(articleId);
    }

    /**
     * 根据ID恢复回收站中的文章
     *
     * @param articleId 文章ID
     * @return 恢复后的文章
     */
    @RequestMapping("/recoverArticle")
    public Article recoverArticle(Integer articleId) {
        return articleService.recoverArticle(articleId);
    }

    /**
     * 根据用户ID和文章ID删除回收站中的文章
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return 删除成功与否
     */
    @RequestMapping("/deleteBackup")
    public Boolean deleteBackup(Integer userId, Integer articleId) {
        articleService.deleteBackup(userId, articleId);
        return true;
    }

    /**
     * 根据用户ID获取回收站的文章
     *
     * @param userId 用户ID
     * @return 该用户在回收站中的所有ID
     */
    @RequestMapping("/getBackup")
    public List<Article> getBackup(Integer userId) {
        return articleService.getBackup(userId);
    }
}
