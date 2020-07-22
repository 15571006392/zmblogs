package com.service;

import com.bean.BlogQuery;
import com.bean.Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Detail getBlog(Long id);

    Detail getAndConvert(Long id);

    Page<Detail> listBlog(Pageable pageable, BlogQuery detail);

    Page<Detail> listBlog(Pageable pageable);

    Page<Detail> listBlog(Long tagId,Pageable pageable);

    Page<Detail> listBlog(String query,Pageable pageable);

    List<Detail> listRecommendBlogTop(Integer size);

    Map<String,List<Detail>> archiveBlog();

    Long countBlog();

    Detail saveBlog(Detail detail);

    Detail updateBlog(Long id,Detail detail);

    void deleteBlog(Long id);
}
