package com.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDeleteDao {

    void deleteComment(int id);
}
