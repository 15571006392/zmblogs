package com.service;

import com.bean.UserInfoModify;

public interface UserInfoModifyService {

    UserInfoModify findAllInfo(int id);

    void modifyAvatar(int id,String avatar);

    void modifyInfo(int id,String nickname,String email);
}
