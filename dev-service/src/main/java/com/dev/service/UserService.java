package com.dev.service;

import com.dev.pojo.Users;
import com.dev.pojo.bo.UserBo;


public interface UserService {
    //判断用户是否存在
    public Boolean queryUserIsExist(String username);
    //创建用户
    public Users createUser(UserBo userBo);
    //登录
    public Users queryUsersForLogin(String username, String password);
}
