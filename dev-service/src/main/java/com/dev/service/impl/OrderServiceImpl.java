package com.dev.service.impl;

import com.dev.mapper.OrdersMapper;
import com.dev.pojo.bo.OrderBO;
import com.dev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void createOrder(OrderBO orderBO) {
        String userId =  orderBO.getUserId();
        String address = orderBO.getAddressId();
        String itemSpec = orderBO.getItemSpecIds();
        Integer payMethod = orderBO.getPayMethod();
        String leftMsg = orderBO.getLeftMsg();

    }
}
