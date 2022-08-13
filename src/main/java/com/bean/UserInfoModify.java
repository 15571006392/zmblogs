package com.bean;

import java.util.Date;

public class UserInfoModify {

    private Integer id;
    private String nickname;
    private String password;
    private String email;
    /**
     * 头像
     */
    private String avatar;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public UserInfoModify() {
    }

    public UserInfoModify(Integer id, String nickname, String password, String email, String avatar, Date createTime, Date updateTime) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
