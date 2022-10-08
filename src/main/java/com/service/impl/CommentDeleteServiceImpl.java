package com.service.impl;

import com.dao.CommentDeleteDao;
import com.service.CommentDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(int id) {
        commentDeleteDao.deleteComment(id);
    }
}
