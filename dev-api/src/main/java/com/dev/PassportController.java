package com.dev;

import com.dev.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/userIsExist")
    public int userIsExist(@RequestParam String username){
        //判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return 500;
        }
        //如果没有查询到，返回500
        Boolean isExist = userService.queryUserIsExist(username);
        if(isExist){
            return 500;
        }
        return 200;
    }
}
