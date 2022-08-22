package com.service.impl;

import com.bean.AllBlogsEntity;
import com.dao.AllBlogsDao;
import com.service.AllBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllBlogsServiceImpl implements AllBlogsService {

    @Autowired
    private AllBlogsDao allBlogsDao;

    @Override
    public List<AllBlogsEntity> findAllBlogsNotCurrentUser(Long id) {
        return allBlogsDao.findAllBlogsNotCurrentUser(id);
    }

    @Override
    public void modifyState(Long id) {
        allBlogsDao.modifyState(id);
    }
}
