package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.enums.PayMethod;
import com.dev.pojo.bo.OrderBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "订单相关",tags = {"用户下订单的相关api"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @ApiOperation(value = "用户下单",notes = "用户下单",httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult create(
            @RequestBody OrderBO orderBO
    ) {
        if(orderBO.getPayMethod() != PayMethod.weixin.type){
            JSONResult.errorMsg("支付方式不正确");
        }
        System.out.println(orderBO.toString());
        return JSONResult.ok();
    }

}
