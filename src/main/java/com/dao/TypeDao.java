package com.dao;

import com.bean.TypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao {

    List<TypeEntity> findType();
}
