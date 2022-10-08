package com.service.impl;

import com.NotFoundException;
import com.bean.LeavingMessage;
import com.bean.MessageEntity;
import com.dao.MessageDao;
import com.dao.MessageRepository;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Zm-Mmm
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageDao messageDao;

    private final RedisTemplate redisTemplate;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,MessageDao messageDao,RedisTemplate redisTemplate) {
        this.messageRepository = messageRepository;
        this.messageDao = messageDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据留言数量查询留言
     * @param count 留言数量
     * @return 留言集合
     */
    @Override
    public List<MessageEntity> findMessageByCount(Integer count) {
        // 先从redis中查
        List<MessageEntity> list = (List<MessageEntity>) redisTemplate.opsForHash().get("menu", "message");
        if(list == null){
            // 从mysql查
            List<MessageEntity> messageByCount = messageDao.findMessageByCount(count);
            // 加入redis
            redisTemplate.opsForHash().put("menu","message",messageByCount);
            // 设置超时时间 1天
            redisTemplate.expire("menu", 60 * 60 * 24, TimeUnit.SECONDS);
            return messageByCount;
        }
        return list;
    }

    /**
     * 提交留言
     * @param name 名称
     * @param mail 邮箱
     * @param message 内容
     * @param avatar 头像
     * @return 1成功，0失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertLeavingMessage(String name, String mail, String message, String avatar) {
        LocalDateTime rightNow = LocalDateTime.now();
        String createTime = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return messageDao.insertLeavingMessage(name, mail, message, createTime, avatar);
    }

    /**
     * 分页查询
     * @param pageable 分页
     * @return 全部留言
     */
    @Override
    public Page<LeavingMessage> listLeavingMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    /**
     * 根据id查询留言
     * @param id 留言id
     * @return 留言
     */
    @Override
    public LeavingMessage getLeavingMessage(Long id) {
        Optional<LeavingMessage> byId = messageRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundException("结果不存在");
        }
        return byId.get();
    }

    /**
     * 删除留言
     * @param id 留言id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLeavingMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
