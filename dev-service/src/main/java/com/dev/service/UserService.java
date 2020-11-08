package com.dev.service;

public interface UserService {
    //判断用户是否存在
    public Boolean queryUserIsExist(String username);

    public void saveStu();
    public void updateStu();
    public void deleteStu();
}
