package com.service.impl;

import com.NotFoundException;
import com.alibaba.fastjson2.JSON;
import com.bean.Type;
import com.bean.TypeEntity;
import com.dao.TypeDao;
import com.dao.TypeRepository;
import com.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Zm-Mmm
 */
@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    private final TypeDao typeDao;

    private final RedisTemplate redisTemplate;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository, TypeDao typeDao,RedisTemplate redisTemplate) {
        this.typeRepository = typeRepository;
        this.typeDao = typeDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 首页分类查询，指定数量，按照分类下的博客数量从大到小排序，过滤草稿状态博客
     * @param count 查询分类的数量
     * @return 分类结果
     */
    @Override
    public List<TypeEntity> findIndexType(Integer count) {
        // 先从redis中找
        List<TypeEntity> indexTypes = (List<TypeEntity>) redisTemplate.opsForHash().get("index", "types");
        if (indexTypes == null) {
            // 从mysql中找
            List<TypeEntity> indexType = typeDao.findIndexType(count);
            // 添加到redis
            redisTemplate.opsForHash().put("index", "types", indexType);
            return indexType;
        } else {
            // 找到了
            return indexTypes;
        }
    }

    /**
     * 查询所有分类
     * @return 分类集合
     */
    @Override
    public List<TypeEntity> findType() {
        // 先从redis找
        List<TypeEntity> typeEntities = (List<TypeEntity>) redisTemplate.opsForHash().get("menu", "types");
        if(typeEntities == null){
            // 从mysql中找
            List<TypeEntity> types = typeDao.findType();
            // 加入到redis
            redisTemplate.opsForHash().put("menu","types",types);
            return types;
        }
        // 结果先转为字符串再转为list集合，避免java.util.LinkedHashMap cannot be cast to 实体类异常
        String types = JSON.toJSONString(typeEntities);
        return JSON.parseArray(types,TypeEntity.class);
    }

    /**
     * 保存分类
     * @param type
     * @return
     */
    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 根据ID查询分类
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Type getType(Long id) {
        Optional<Type> optional =  typeRepository.findById(id);
        return optional.get();
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"details.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Optional<Type> optional = typeRepository.findById(id);
        if(!optional.isPresent()){
            throw new NotFoundException("该分类不存在");
        }
        Type type1 = optional.get();
        BeanUtils.copyProperties(type,type1);
        return typeRepository.save(type1);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    /**
     * 通过名字查询
     */
    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
