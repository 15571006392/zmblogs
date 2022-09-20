package com.bean;

/**
 * @author Zm-Mmm
 */
public class TypeEntity {

    private Integer id;
    private String name;
    private Integer blogCount;

    public TypeEntity() {
    }

    public TypeEntity(Integer id, String name, Integer blogCount) {
        this.id = id;
        this.name = name;
        this.blogCount = blogCount;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
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
