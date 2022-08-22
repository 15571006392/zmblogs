package com.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserInfo {

    private Integer id;
    private String avatar;
    @JsonFormat(pattern = "yyyy年MM月dd日 a")
    private Date createTime;
    private String email;
    private String nickname;
    @JsonFormat(pattern = "yyyy年MM月dd日 a")
    private Date updateTime;
    private Integer blogCount;
    private Integer type;

    public UserInfo() {
    }

    public UserInfo(Integer id, String avatar, Date createTime, String email, String nickname, Date updateTime, Integer blogCount, Integer type) {
        this.id = id;
        this.avatar = avatar;
        this.createTime = createTime;
        this.email = email;
        this.nickname = nickname;
        this.updateTime = updateTime;
        this.blogCount = blogCount;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
