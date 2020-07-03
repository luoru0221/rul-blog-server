package com.rul.blog.mapper;

import com.rul.blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackupMapper {

    /**
     * 添加备份
     *
     * @param article 待添加的文章基本信息
     */
    void addBackup(Article article, Integer userId);

    /**
     * 查询文章备份信息
     *
     * @param articleId 文章Id
     */
    Article findBackup(Integer articleId);

    /**
     * 删除备份
     *
     * @param articleId 文章Id
     */
    void delBackup(Integer articleId);

    /**
     * 查询某个用户的所有备份文件
     *
     * @param userId 用户ID
     * @return 该用户的所有备份文件
     */
    List<Article> findBackupByUserId(Integer userId);
}
