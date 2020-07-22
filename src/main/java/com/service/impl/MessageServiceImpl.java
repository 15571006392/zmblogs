package com.service.impl;

import com.bean.LeavingMessage;
import com.dao.MessageRepository;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public int updateLeavingMessage(String name, String mail, String message) {

        LocalDateTime rightNow = LocalDateTime.now();
        String rightNow2 = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LeavingMessage leavingMessage = new LeavingMessage();
        leavingMessage.setMessage(message);
        leavingMessage.setMail(mail);
        leavingMessage.setName(name);
        leavingMessage.setCt(rightNow2);
        if(messageRepository.save(leavingMessage) != null){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<LeavingMessage> getLeavingMessage() {
        return messageRepository.findAll();
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Transactional
    public Page<LeavingMessage> listLeavingMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public LeavingMessage getLeavingMessage(Long id) {
        Optional<LeavingMessage> byId = messageRepository.findById(id);
        LeavingMessage message = byId.get();
        return message;
    }

    @Override
    public void deleteLeavingMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
