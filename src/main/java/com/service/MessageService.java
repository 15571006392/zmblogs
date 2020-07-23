package com.service;

import com.bean.LeavingMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zm-Mmm
 */
public interface MessageService {
    /**
     * 留言信息提交
     * @param name
     * @param mail
     * @param message
     * @return
     */
    int updateLeavingMessage(String name,String mail,String message);

    /**
     * 获取所有留言信息
     * @return
     */
    List<LeavingMessage> getLeavingMessage();

    /**
     * 分页查询留言信息
     * @param pageable
     * @return
     */
    Page<LeavingMessage> listLeavingMessage(Pageable pageable);

    /**
     * 根据id查询留言
     * @param id
     * @return
     */
    LeavingMessage getLeavingMessage(Long id);

    /**
     * 删除留言
     * @param id
     */
    void deleteLeavingMessage(Long id);
}
