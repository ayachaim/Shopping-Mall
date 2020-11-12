package com.dev.controller;

import com.dev.Utils.JSONResult;
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
}
