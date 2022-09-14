package com.dao;

import com.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    /**
     * 根据名字查询分类
     * @param name 分类名称
     * @return 分类
     */
    Type findByName(String name);

    /**
     * 分页查询
     * 查询分类
     * @param pageable 分页
     * @return 分类
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
