package com.bean;

import java.util.Date;

public class BlogEntity {

    private String BlogUsername;
    private String BlogUserAvatar;
    private String typeName;
    private Integer typeId;
    private Integer userId;

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 首图
     */
    private String firstPicture;
    /**
     * 标记
     */
    private String flag;
    /**
     * 浏览次数
     */
    private Integer views;
    /**
     * 赞赏是否开启
     */
    private boolean appreciation;
    /**
     * 转载声明开启
     */
    private boolean shareStatement;
    /**
     * 评论开启
     */
    private boolean comment;
    /**
     * 是否发布
     */
    private boolean published;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 描述
     */
    private String description;
    /**
     * 发布日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBlogUsername() {
        return BlogUsername;
    }

    public void setBlogUsername(String blogUsername) {
        BlogUsername = blogUsername;
    }

    public String getBlogUserAvatar() {
        return BlogUserAvatar;
    }

    public void setBlogUserAvatar(String blogUserAvatar) {
        BlogUserAvatar = blogUserAvatar;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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

    public BlogEntity(String blogUsername, String blogUserAvatar, String typeName, Integer typeId, Integer userId, Integer id, String title, String content, String firstPicture, String flag, Integer views, boolean appreciation, boolean shareStatement, boolean comment, boolean published, boolean recommend, String description, Date createTime, Date updateTime) {
        BlogUsername = blogUsername;
        BlogUserAvatar = blogUserAvatar;
        this.typeName = typeName;
        this.typeId = typeId;
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.comment = comment;
        this.published = published;
        this.recommend = recommend;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BlogEntity() {
    }
}
