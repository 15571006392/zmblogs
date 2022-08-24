package com.bean;

public class TypeEntity {

    private Integer id;
    private String name;
    private Integer BlogCount;

    public Integer getBlogCount() {
        return BlogCount;
    }

    public void setBlogCount(Integer blogCount) {
        BlogCount = blogCount;
    }

    public TypeEntity() {
    }

    public TypeEntity(Integer id, String name, Integer blogCount) {
        this.id = id;
        this.name = name;
        BlogCount = blogCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
