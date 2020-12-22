package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.pojo.bo.ShopCartBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车",tags = {"购物车数据"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {
    @ApiOperation(value = "添加购物车",notes = "添加购物车",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId
            @RequestBody ShopCartBo shopCartBo,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("用户为空");
        }
        System.out.println(shopCartBo);
        //TODO
        return JSONResult.ok();
    }

}
