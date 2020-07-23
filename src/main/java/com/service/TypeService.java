package com.service;

import com.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
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
     * 查询所有分类
     * @return
     */
    List<Type> listType();

    /**
     * 根据指定大小
     * 查询指定数量的分类
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 修改分类
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 通过id删除分类
     * @param id
     */
    void deleteType(Long id);

    /**
     * 通过名字查询分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);
}
