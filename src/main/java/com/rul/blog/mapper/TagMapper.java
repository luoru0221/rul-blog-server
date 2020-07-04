package com.rul.blog.mapper;

import com.rul.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Tag持久层接口
 *
 * @author RuL
 * @time 2020-06-29
 */
@Mapper
public interface TagMapper {

    /**
     * 查找tags中已经存在标签
     *
     * @param tags tags
     * @return tags中已经存在的标签
     */
    List<Tag> findTagsByName(List<Tag> tags);

    /**
     * 添加新标签
     *
     * @param tags 新标签集合
     */
    void addTagsByName(List<Tag> tags);

    /**
     * 为文章添加标签
     *
     * @param articleId 文章Id
     * @param tags      新标签
     */
    void addArticleTags(int articleId, List<Tag> tags);

    /**
     * 删除不在最新标签集合中的标签
     *
     * @param articleId 文章标签
     * @param tags      最新标签
     */
    void deleteArticleTags(int articleId, List<Tag> tags);

    /**
     * 删除文章的全部标签
     *
     * @param articleId 文章Id
     */
    void deleteArticleAllTags(int articleId);
}
