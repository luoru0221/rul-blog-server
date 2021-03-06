package com.rul.blog.pojo;

import java.io.Serializable;

public class Tag implements Serializable {
    private Integer tagId;
    private String tagName;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
