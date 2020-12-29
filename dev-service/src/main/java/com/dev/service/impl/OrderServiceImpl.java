package com.dev.service.impl;

import com.dev.mapper.OrdersMapper;
import com.dev.pojo.Orders;
import com.dev.pojo.bo.OrderBO;
import com.dev.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private Sid sid;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void createOrder(OrderBO orderBO) {
        String userId =  orderBO.getUserId();
        String address = orderBO.getAddressId();
        String itemSpec = orderBO.getItemSpecIds();
        Integer payMethod = orderBO.getPayMethod();
        String leftMsg = orderBO.getLeftMsg();
        //邮费设置为0
        Integer postAmount = 0;
        //生成16位订单id
        String orderId = sid.nextShort();
        Orders newOrder = new Orders();
        //设置订单id
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        //TODO 其他的db字段都要设置进去
    }
}
