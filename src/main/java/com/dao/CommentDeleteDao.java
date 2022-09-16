package com.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface CommentDeleteDao {

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(int id);
}
