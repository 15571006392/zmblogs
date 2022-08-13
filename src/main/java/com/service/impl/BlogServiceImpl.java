package com.service.impl;

import com.bean.BlogQuery;
import com.bean.Detail;
import com.bean.Type;
import com.bean.UserDetail;
import com.dao.BlogRepository;
import com.dao.DetailDao;
import com.service.BlogService;
import com.util.Markdown;
import com.util.NullBeanProperties;
import com.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author Zm-Mmm
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private DetailDao detailDao;

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    @Override
    public Detail getBlog(Long id) {
        Optional<Detail> byId = blogRepository.findById(id);
        Detail detail = byId.get();
        return detail;
    }

    @Override
    public List<UserDetail> selectDetailFromUserIdLimit(Long id) {
        return detailDao.selectDetailFromUserIdLimit(id);
    }

    /**
     * markdown转html
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Detail getAndConvert(Long id) {
        Optional<Detail> byId = blogRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotFoundException("该博客不存在");
        }
        Detail detail = byId.get();
        Detail b = new Detail();
        BeanUtils.copyProperties(detail,b);
        String content = b.getContent();
        b.setContent( Markdown.markdownToHtmlExtensions(content));

        /**
         * 访问次数磊加
         */
        blogRepository.updateViews(id);
        return b;
    }

    /**
     * 分页动态查询
     * @param pageable
     * @param detail
     * @return
     */
    @Override
    public Page<Detail> listBlog(Pageable pageable, BlogQuery detail) {
        return blogRepository.findAll(new Specification<Detail>() {
            @Override
            public Predicate toPredicate(Root<Detail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // Root<Detail> : 查什么
                // CriteriaQuery : 容器
                // CriteriaBuilder : 表达式
                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(detail.getTitle()) && detail.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+detail.getTitle()+"%"));
                }
                if(detail.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),detail.getTypeId()));
                }
                if(detail.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.get("recommend"),detail.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    /**
     * 分页查找全部博客
     * @param pageable
     * @return
     */
    @Override
    public Page<Detail> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * 分页查询关联的标签
     * @param tagId
     * @param pageable
     * @return
     */
    @Override
    public Page<Detail> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Detail>() {
            /**
             * 关联查询
             * @param root
             * @param criteriaQuery
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Detail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Detail> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public List<Detail> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Detail>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String,List<Detail>> map = new HashMap<>();
        for(String year : years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    /**
     * 总数
     * @return
     */
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    /**
     * 保存博客
     * @param detail
     * @return
     */
    @Override
    @Transactional
    public Detail saveBlog(Detail detail) {
        // 首次创建，初始化属性
        if(detail.getId() == null){
            detail.setCreateTime(new Date());
            detail.setUpdateTime(new Date());
            detail.setViews(0);
        }else{
            detail.setUpdateTime(new Date());
        }
        return blogRepository.save(detail);
    }

    /**
     * 新增博客
     * @param id
     * @param detail
     * @return
     */
    @Override
    @Transactional
    public Detail updateBlog(Long id, Detail detail) {
        Optional<Detail> byId = blogRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotFoundException("该博客不存在");
        }
        Detail detail1 = byId.get();
        // 获取非空元素，复制，防止覆盖createTime和views
        BeanUtils.copyProperties(detail,detail1, NullBeanProperties.getNullProperties(detail));
        // 更新时间
        detail1.setUpdateTime(new Date());
        return blogRepository.save(detail1);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
