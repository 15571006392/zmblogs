package com.service.impl;

import com.NotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Zm-Mmm
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<TypeEntity> findType() {
        return typeDao.findType();
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
        Sort sort = new Sort(Sort.Direction.DESC,"details.size");
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
