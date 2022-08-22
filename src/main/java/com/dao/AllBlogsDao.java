package com.dao;

import com.bean.AllBlogsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AllBlogsDao {

    List<AllBlogsEntity> findAllBlogsNotCurrentUser(Long id);

    void modifyState(Long id);
}
