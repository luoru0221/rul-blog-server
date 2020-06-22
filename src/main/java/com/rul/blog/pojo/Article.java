package com.rul.blog.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Article implements Serializable {
    //文章Id
    private Integer articleId;
    //文章标题
    private String articleTitle;
    //发布时间
    private Date articleTime;
    //作者
    private String articleAuthor;
    //文章内容
    private String articleContent;
    //文章标签
    private List<Tag> tags;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleTime=" + articleTime +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", tags=" + tags +
                '}';
    }
}
