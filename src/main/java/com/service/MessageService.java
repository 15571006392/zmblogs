package com.service;

import com.bean.LeavingMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    int updateLeavingMessage(String name,String mail,String message);

    List<LeavingMessage> getLeavingMessage();

    Page<LeavingMessage> listLeavingMessage(Pageable pageable);

    LeavingMessage getLeavingMessage(Long id);

    void deleteLeavingMessage(Long id);
}
