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
     *  提交留言
     * @param name 名称
     * @param mail 邮箱
     * @param message 内容
     * @param avatar 头像
     * @return 成功
     */
    int updateLeavingMessage(String name,String mail,String message,String avatar);
    /**
     * 获取所有留言信息
     * @return 所有留言
     */
    List<LeavingMessage> getLeavingMessage();

    /**
     * 分页查询留言信息
     * @param pageable 分页
     * @return 留言
     */
    Page<LeavingMessage> listLeavingMessage(Pageable pageable);

    /**
     * 根据id查询留言
     * @param id 留言id
     * @return 留言
     */
    LeavingMessage getLeavingMessage(Long id);

    /**
     * 删除留言
     * @param id 留言id
     */
    void deleteLeavingMessage(Long id);
}
