package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.pojo.bo.ShopCartBo;
import com.dev.pojo.vo.ShopCartVO;
import com.dev.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "购物车",tags = {"购物车数据"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {

    @Autowired
    private ItemService itemsService;

    @ApiOperation(value = "添加购物车",notes = "添加购物车",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId,
            @RequestBody ShopCartBo shopCartBo,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("用户为空");
        }
        System.out.println(shopCartBo);
        //TODO redis
        return JSONResult.ok();
    }


    @ApiOperation(value = "根据商品规格id查询最新的商品",notes = "根据商品规格id查询最新的商品",httpMethod = "GET")
    @GetMapping("/refresh")
    public JSONResult refresh(
            @ApiParam(name = "specIds" , value = "字符串拼接规格ids" , required = true , example = "1,2,3")
            @RequestParam String specIds
    ) {
        if(StringUtils.isBlank(specIds)){
            return JSONResult.ok();
        }
        List<ShopCartVO> list = itemsService.queryItemsBySpecIds(specIds);
        return JSONResult.ok(list);
    }

}
