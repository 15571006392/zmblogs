package com.service.impl;

import com.dao.CommentDeleteDao;
import com.service.CommentDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zm-Mmm
 */
@Service
public class CommentDeleteServiceImpl implements CommentDeleteService {

    private final CommentDeleteDao commentDeleteDao;

    @Autowired
    public CommentDeleteServiceImpl(CommentDeleteDao commentDeleteDao) {
        this.commentDeleteDao = commentDeleteDao;
    }

    @Override
    public void deleteComment(int id) {
        commentDeleteDao.deleteComment(id);
    }
}
