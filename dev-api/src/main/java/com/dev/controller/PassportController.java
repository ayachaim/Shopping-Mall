package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.Utils.MD5Utils;
import com.dev.pojo.Users;
import com.dev.pojo.bo.UserBo;
import com.dev.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "注册登录",tags = {"用于注册登录相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/userIsExist")
    public JSONResult userIsExist(@RequestParam String username){
        //判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return JSONResult.errorMsg("用户名不能为空");
        }
        //用户名已存在
        Boolean isExist = userService.queryUserIsExist(username);
        if(isExist){
            return JSONResult.errorMsg("用户名已存在");
        }
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/register")
    public JSONResult register(@RequestBody UserBo userBo){

        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String conFirmPwd = userBo.getConfirmPassword();
        //判断用户密码不能为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(conFirmPwd)){
            return JSONResult.errorMsg("用户名和密码不能为空");
        }
        //判断用户名是否存在
        Boolean isExist = userService.queryUserIsExist(username);
        if(isExist){
            return JSONResult.errorMsg("用户名已存在");
        }
        //判断密码的长度不能少于6位
        if(password.length() < 6){
            return JSONResult.errorMsg("密码长度不能少于6位");
        }
        //两次输入密码必须一致
        if(!password.equals(conFirmPwd)){
            return JSONResult.errorMsg("两次输入密码必须一致");
        }
        //注册
        userService.createUser(userBo);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBo userBo) throws Exception{
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        //判断用户密码不能为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名和密码不能为空");
        }
        //判断用户名是否存在
        Users loginResult = userService.queryUsersForLogin(username, MD5Utils.getMD5Str(password));
        if(loginResult==null){
            return JSONResult.errorMsg("用户名和密码不正确");
        }
        loginResult = setNullProperty(loginResult);
        System.out.println(loginResult);
        return JSONResult.ok(loginResult);
    }

    private Users setNullProperty(Users loginResult){
        loginResult.setPassword(null);
        loginResult.setMobile(null);
        loginResult.setEmail(null);
        loginResult.setCreatedTime(null);
        loginResult.setUpdatedTime(null);
        loginResult.setBirthday(null);
        return loginResult;
    }
}
