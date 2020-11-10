package com.bean;

import javax.persistence.*;

/**
 * @author Zm-Mmm
 */
@Entity
@Table(name = "t_leaving_message")
public class LeavingMessage {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String mail;
    private String message;
    /**
     * 留言日期
     */
    private String ct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LeavingMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", message='" + message + '\'' +
                ", currentTime=" + ct +
                '}';
    }
}
