package com.bean;

/**
 * @author Zm-Mmm
 */
public class AboutEntity {

    private Integer id;

    private Integer allCount;

    private Integer registerUserCount;

    private String onlineDate;

    @Override
    public String toString() {
        return "AboutEntity{" +
                "id=" + id +
                ", allCount=" + allCount +
                ", registerUserCount=" + registerUserCount +
                ", onlineDate='" + onlineDate + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }


    public Integer getRegisterUserCount() {
        return registerUserCount;
    }

    public void setRegisterUserCount(Integer registerUserCount) {
        this.registerUserCount = registerUserCount;
    }

    public String getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(String onlineDate) {
        this.onlineDate = onlineDate;
    }
}
