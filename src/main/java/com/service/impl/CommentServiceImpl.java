package com.service.impl;

import com.NotFoundException;
import com.bean.Comment;
import com.dao.CommentRepository;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Zm-Mmm
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by("createTime");
        List<Comment> comments = commentRepository.findByDetailIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            Optional<Comment> byId = commentRepository.findById(parentCommentId);
            if (!byId.isPresent()) {
                throw new NotFoundException("结果不存在");
            }
            Comment comment1 = byId.get();
            comment.setParentComment(comment1);
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments 评论
     * @return 评论集合
     */
    private List<Comment> eachComment(List<Comment> comments) {
        /*
          评论功能第一级createTime和adminComment遗留问题
         */
        /*List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }*/
        //合并评论的各层子代到第一级子代集合中
        combineChildren(comments);
        return comments;
    }

    /**
     * @param comments root根节点，blog不为空的对象集合
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * 存放迭代找出的所有子代的集合
     */
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     */
    private void recursively(Comment comment) {
        //顶节点添加到临时存放集合
        tempReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}
