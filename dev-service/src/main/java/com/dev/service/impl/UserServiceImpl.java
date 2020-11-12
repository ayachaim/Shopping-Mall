package com.dev.service.impl;

import com.dev.Utils.DateUtil;
import com.dev.Utils.MD5Utils;
import com.dev.enums.Sex;
import com.dev.mapper.UsersMapper;
import com.dev.pojo.Users;
import com.dev.pojo.bo.UserBo;
import com.dev.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2719292398,3645159946&fm=26&gp=0.jpg";

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
        String userId = sid.nextShort();
        Users user = new Users();
        //全局唯一userid
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        user.setNickname(userBo.getUsername());
        //密码md5加密
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认头像
        user.setFace(USER_FACE);
        //默认1990-1-1
        user.setBirthday(DateUtil.stringToDate("1990-1-1"));
        //默认性别保密
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        //插入user信息
        usersMapper.insert(user);
        return user;
    }

}
