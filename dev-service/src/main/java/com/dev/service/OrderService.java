package com.dev.service;


import com.dev.pojo.bo.OrderBO;

public interface OrderService {
    public String createOrder(OrderBO orderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId,Integer orderStatus);
}
