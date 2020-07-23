package com.dao;

import com.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * 根据创建时间排序
     * 根据博客id查找不为空的评论
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findByDetailIdAndParentCommentNull(Long blogId, Sort sort);
}
