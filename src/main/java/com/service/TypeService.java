package com.service;

import com.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    /**
     * 新增分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据ID查询分类
     * @param id
     * @return
     */
    Type getType(Long id);
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 查询所有数据
     */
    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    /**
     * 修改
     */
    Type updateType(Long id,Type type);

    void deleteType(Long id);

    /**
     * 通过名字查询
     */
    Type getTypeByName(String name);
}
