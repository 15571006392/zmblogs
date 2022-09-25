package com.dao;

import com.bean.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zm-Mmm
 */
@Mapper
public interface MessageDao {

    /**
     * 获取指定数量的留言
     * @param count 留言数量
     * @return 留言集合
     */
    List<MessageEntity> findMessageByCount(Integer count);


    /**
     * 提交留言
     * @param name 名称
     * @param mail 邮箱
     * @param message 留言内容
     * @param createTime 创建时间
     * @param avatar 头像
     * @return 1成功0失败
     */
    int insertLeavingMessage(String name,String mail,String message,String createTime,String avatar);
}
