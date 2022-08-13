package com.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserDetail {

    private Long id;
    /**
     * 标题
     */
    private String title;
    private String name;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 是否发布
     */
    private boolean published;
    /**
     * 浏览次数
     */
    private Integer views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:Ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:Ss")
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UserDetail() {
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", recommend=" + recommend +
                ", published=" + published +
                ", views=" + views +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
