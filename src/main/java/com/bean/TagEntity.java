package com.bean;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public class TagEntity {

    private Integer id;
    private String name;
    private List<BlogEntity> blogEntities;
    private Integer BlogCount;

    public Integer getBlogCount() {
        return BlogCount;
    }

    public void setBlogCount(Integer blogCount) {
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

    public List<BlogEntity> getBlogEntities() {
        return blogEntities;
    }

    public void setBlogEntities(List<BlogEntity> blogEntities) {
        this.blogEntities = blogEntities;
    }

    public TagEntity(Integer id, String name, List<BlogEntity> blogEntities, Integer blogCount) {
        this.id = id;
        this.name = name;
        this.blogEntities = blogEntities;
        BlogCount = blogCount;
    }

    public TagEntity() {
    }
}
