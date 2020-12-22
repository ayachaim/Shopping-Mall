package com.dev.controller;

import com.dev.Utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车",tags = {"购物车数据"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {
    @ApiOperation(value = "添加购物车",notes = "添加购物车",httpMethod = "GET")
    @GetMapping("/add")
    public JSONResult add(
            @RequestParam String userId
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        return JSONResult.ok();
    }

}
