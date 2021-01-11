package com.dev.controller;

import com.dev.Utils.CookieUtils;
import com.dev.Utils.JSONResult;
import com.dev.enums.OrdersStatus;
import com.dev.enums.PayMethod;
import com.dev.pojo.bo.OrderBO;
import com.dev.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关",tags = {"用户下订单的相关api"})
@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController{

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "用户下单",notes = "用户下单",httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult create (
            @RequestBody OrderBO orderBO,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        if(orderBO.getPayMethod() != PayMethod.weixin.type){
             return JSONResult.errorMsg("支付方式不正确");
        }
        if(StringUtils.isBlank(orderBO.getItemSpecIds())){
            return JSONResult.errorMsg("购物车不能为空");
        }
        System.out.println(orderBO.toString() + "打印的信息123123123");
        System.out.println(orderBO.toString() + "打印的信息666666");
        //创建订单
        String orderId = orderService.createOrder(orderBO);

        // TODO 整合购物车以后，redis需要清除购物车已经结算的商品。更新到cookie
        CookieUtils.setCookie(httpServletRequest,httpServletResponse,SHOPCARD,"",true);
        return JSONResult.ok(orderId);
    }

    @ApiOperation(value = "用户付款以及修改订单状态",notes = "用户付款以及修改订单状态",httpMethod = "POST")
    @PostMapping("/notifyMerchantOrderPaid")
    public JSONResult notifyMerchantOrderPaid(String merchantOrderId){
        orderService.updateOrderStatus(merchantOrderId, OrdersStatus.WAIT_DELIVER.type);
        return JSONResult.ok();
    }

}
