package com.dev.service.impl;

import com.dev.enums.YesOrNo;
import com.dev.mapper.OrdersMapper;
import com.dev.pojo.Orders;
import com.dev.pojo.UserAddress;
import com.dev.pojo.bo.OrderBO;
import com.dev.service.AddressService;
import com.dev.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    private AddressService addressService;
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
        //查询
        UserAddress userAddress = addressService.queryUserAddres(userId,address);
        //设置订单id
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverAddress(userAddress.getProvince() + " "
                + userAddress.getCity() + " "
                + userAddress.getDistrict() + " "
                + userAddress.getDetail());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverName(userAddress.getReceiver());
        // TODO 其他的db字段都要设置进去
//        newOrder.setTotalAmount();
//        newOrder.setRealPayAmount();
        //免费包邮
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setIsComment(YesOrNo.No.type);
        newOrder.setIsDelete(YesOrNo.No.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());



    }
}
