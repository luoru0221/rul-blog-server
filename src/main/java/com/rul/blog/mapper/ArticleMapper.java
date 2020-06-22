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
     * 根据id查找文章信息
     *
     * @return 该文章的全部文章信息
     */
    Article findArticleById(Integer articleId);
}
