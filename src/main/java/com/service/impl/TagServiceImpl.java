package com.service.impl;

import com.NotFoundException;
import com.bean.Tag;
import com.dao.TagRepository;
import com.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存标签
     * @param tag
     * @return
     */
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @Transactional
    public Tag getTag(Long id) {
        Optional<Tag> byId = tagRepository.findById(id);
        Tag tag = byId.get();
        return tag;
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Transactional
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * 查找全部
     * @return
     */
    @Transactional
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * 通过多个ID查找
     * @param ids
     * @return
     */
    @Transactional
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"details.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] array = ids.split(",");
            for (int i = 0; i < array.length; i++) {
                list.add(new Long(array[i]));
            }
        }
        return list;
    }

    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Optional<Tag> byId = tagRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotFoundException("该标签不存在");
        }
        Tag tag1 = byId.get();
        BeanUtils.copyProperties(tag,tag1);
        return tagRepository.save(tag1);
    }

    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
