package com.service;

import com.bean.Comment;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface CommentService {
    /**
     * 根据博客ID查找所有评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
