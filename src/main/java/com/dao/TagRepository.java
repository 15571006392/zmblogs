package com.dao;

import com.bean.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * 根据标签名字查找标签
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 分页查询
     * 查询标签
     * @param pageable
     * @return
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
