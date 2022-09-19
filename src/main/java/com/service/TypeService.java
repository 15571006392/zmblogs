package com.service;

import com.bean.Type;
import com.bean.TypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface TypeService {

    /**
     * 首页分类查询，指定数量，按照分类下的博客数量从大到小排序，过滤草稿状态博客
     * @param count 查询分类的数量
     * @return 分类结果
     */
    List<TypeEntity> findIndexType(Integer count);

    /**
     * mybatis重构
     * 查询所有分类
     * @return 分类的list集合
     */
     List<TypeEntity> findType();

    /**
     * 新增分类
     * @param type 分类
     * @return 分类
     */
    Type saveType(Type type);

    /**
     * 根据ID查询分类
     * @param id 分类id
     * @return 分类
     */
    Type getType(Long id);
    /**
     * 分页查询
     * @param pageable 分页
     * @return 分类
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 查询所有分类
     * @return 分类
     */
    List<Type> listType();

    /**
     * 根据指定大小
     * 查询指定数量的分类
     * @param size 大小
     * @return 分类
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 修改分类
     * @param id 分类id
     * @param type 分类
     * @return 分类
     */
    Type updateType(Long id,Type type);

    /**
     * 通过id删除分类
     * @param id 分类id
     */
    void deleteType(Long id);

    /**
     * 通过名字查询分类
     * @param name 分类名称
     * @return 分类
     */
    Type getTypeByName(String name);
}
