package com.dev.service.impl;

import com.dev.mapper.UsersMapper;
import com.dev.pojo.Users;
import com.dev.pojo.bo.UserBo;
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
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users user = new Users();
        user.setUsername(userBo.getUsername());
        //密码md5加密
        user.setUsername(userBo.getPassword());
        user.setNickname(userBo.getPassword());
        return null;
    }

}
