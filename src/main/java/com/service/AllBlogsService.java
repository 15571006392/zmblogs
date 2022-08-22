package com.service;

import com.bean.AllBlogsEntity;

import java.util.List;

public interface AllBlogsService {
    List<AllBlogsEntity> findAllBlogsNotCurrentUser(Long id);

    void modifyState(Long id);
}
