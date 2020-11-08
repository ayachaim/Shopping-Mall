package com.dev.service.impl;

import com.dev.mapper.UsersMapper;
import com.dev.pojo.Users;
import com.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Boolean queryUserIsExist(String username) {
        Example userExample = new Example(Users.class);
        System.out.println(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result == null ? false : true;
//        usersMapper.selectByExample()
    }

    @Override
    public void saveStu() {

    }

    @Override
    public void updateStu() {

    }

    @Override
    public void deleteStu() {

    }
}
