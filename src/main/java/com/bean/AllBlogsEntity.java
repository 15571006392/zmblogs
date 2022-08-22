package com.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AllBlogsEntity {

    private String BlogUserName;

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 分类
     */
    private String name;
    /**
     * 分类的ID
     */
    private Integer nameId;
    /**
     * 是否推荐
     */
    private boolean recommend;

    /**
     * 描述
     */
    private String description;

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

    public AllBlogsEntity(String blogUserName, Long id, String title, String name, Integer nameId, boolean recommend, String description, boolean published, Integer views, Date createTime, Date updateTime) {
        BlogUserName = blogUserName;
        this.id = id;
        this.title = title;
        this.name = name;
        this.nameId = nameId;
        this.recommend = recommend;
        this.description = description;
        this.published = published;
        this.views = views;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public AllBlogsEntity() {
    }

    public String getBlogUserName() {
        return BlogUserName;
    }

    public void setBlogUserName(String blogUserName) {
        BlogUserName = blogUserName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
