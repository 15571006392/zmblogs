package com.dao;

import com.bean.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface BlogRepository extends JpaRepository<Detail, Long> , JpaSpecificationExecutor<Detail> {

    /**
     *分页查找推荐的博客
     * @param pageable
     * @return
     */
    @Query("select b from Detail b where b.recommend = true")
    List<Detail> findTop(Pageable pageable);

    /**
     * 分页模糊查询
     * 查找标题或文章内容有没有指定关键字
     * @param query
     * @param pageable
     * @return
     */
    @Query("select b from Detail b where b.title like ?1 or b.content like ?1")
    Page<Detail> findByQuery(String query,Pageable pageable);

    /**
     * 更新文章的浏览量
     * @param id
     * @return
     */
    @Modifying
    @Query("update Detail b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    /**
     * 查找文章的创建日期
     * 按年份格式化日期
     * 分组
     * @return
     */
    @Query("select function('date_format',b.createTime,'%Y') from Detail b group by function('date_format',b.createTime,'%Y')")
    List<String> findGroupYear();

    /**
     * 查找指定年份的文章
     * 按年份格式化日期
     * @param year
     * @return
     */
    @Query("select b from Detail b where function('date_format',b.createTime,'%Y') = ?1")
    List<Detail> findByYear(String year);
}
