package com.service.impl;

import com.dao.CommentDeleteDao;
import com.service.CommentDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDeleteServiceImpl implements CommentDeleteService {

    @Autowired
    private CommentDeleteDao commentDeleteDao;

    @Override
    public void deleteComment(int id) {
        commentDeleteDao.deleteComment(id);
    }
}
