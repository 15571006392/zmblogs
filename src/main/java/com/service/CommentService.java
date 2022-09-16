package com.service;

import com.bean.Comment;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface CommentService {
    /**
     * 根据博客ID查找所有评论
     * @param blogId 博客id
     * @return 评论
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment 评论
     * @return 评论
     */
    Comment saveComment(Comment comment);
}
