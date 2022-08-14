package com.dao;

import com.bean.UserDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailDao {

    List<UserDetail> selectDetailFromUserIdLimit(Long id);
}