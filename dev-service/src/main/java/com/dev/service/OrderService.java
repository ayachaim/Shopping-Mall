package com.dev.service;


import com.dev.pojo.bo.OrderBO;
import com.dev.pojo.vo.OrdersVO;

public interface OrderService {
    public OrdersVO createOrder(OrderBO orderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId,Integer orderStatus);
}
