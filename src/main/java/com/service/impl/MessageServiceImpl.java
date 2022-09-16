package com.service.impl;

import com.bean.LeavingMessage;
import com.dao.MessageRepository;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author Zm-Mmm
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * 保存留言
     * @param name 名称
     * @param mail 邮箱
     * @param message 信息
     * @return 是否成功
     */
    @Override
    public int updateLeavingMessage(String name, String mail, String message, String avatar) {
        // 线程安全
        LocalDateTime rightNow = LocalDateTime.now();
        String rightNow2 = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LeavingMessage leavingMessage = new LeavingMessage();
        // 初始化留言信息
        leavingMessage.setMessage(message);
        leavingMessage.setMail(mail);
        leavingMessage.setName(name);
        leavingMessage.setCt(rightNow2);
        leavingMessage.setAvatar(avatar);
        messageRepository.save(leavingMessage);
        return 1;
    }
    /**
     * 查询全部留言
     * @return 全部留言
     */
    @Override
    public List<LeavingMessage> getLeavingMessage() {
        return messageRepository.findAll();
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
        return byId.get();
    }

    /**
     * 删除留言
     * @param id 留言id
     */
    @Override
    public void deleteLeavingMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
