package com.rul.blog.mapper;

import com.rul.blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Article持久层接口
 *
 * @author RuL
 */
@Mapper
public interface ArticleMapper {

    /**
     * 查询所有文章信息
     *
     * @return 不包含文章内容的所有文章信息
     */
    List<Article> findAllArticle();

    /**
     * 根据作者ID查询文章
     *
     * @param authorId 作者ID
     * @return 该作者的所有文章
     */
    List<Article> findArticleByAuthor(Integer authorId);

    /**
     * 根据ID查找文章信息
     *
     * @return 该文章的全部文章信息
     */
    Article findArticleById(Integer articleId);

    /**
     * 根据标签Id查找文章信息
     *
     * @param tagId 标签Id
     * @return 拥有该标签的所有文章
     */
    List<Article> findArticleByTagId(Integer tagId);

    /**
     * 插入新文章信息到数据库
     *
     * @param article 新文章信息
     */
    void addArticle(Article article);

    /**
     * 根据文章Id查询作者Id
     *
     * @param articleId 文章Id
     * @return 作者Id
     */
    Integer findAuthorIdByArticleId(Integer articleId);

    /**
     * 更新文章标题和内容
     *
     * @param article 文章内容
     */
    Integer updateTitleAndContent(Article article);

    /**
     * 根据文章Id删除文章
     *
     * @param articleId 文章ID
     * @return 删除成功与否
     */
    Integer deleteArticleById(Integer articleId);

}
