package com.service.impl;

import com.bean.BlogEntity;
import com.dao.AllBlogsDao;
import com.service.AllBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Service
public class AllBlogsServiceImpl implements AllBlogsService {

    private final AllBlogsDao allBlogsDao;

    @Autowired
    public AllBlogsServiceImpl(AllBlogsDao allBlogsDao) {
        this.allBlogsDao = allBlogsDao;
    }

    @Override
    public List<BlogEntity> findAllBlogsNotCurrentUser(Long id) {
        return allBlogsDao.findAllBlogsNotCurrentUser(id);
    }

    @Override
    public void modifyState(Long id) {
        allBlogsDao.modifyState(id);
    }
}
