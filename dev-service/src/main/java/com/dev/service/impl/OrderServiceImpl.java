package com.dev.service.impl;

import com.dev.enums.YesOrNo;
import com.dev.mapper.OrdersMapper;
import com.dev.pojo.*;
import com.dev.pojo.bo.OrderBO;
import com.dev.service.AddressService;
import com.dev.service.ItemService;
import com.dev.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;
    @Autowired
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

        //设置子表的信息
        String idArr[] = itemSpec.split(",");
        //价格为0
        Integer totalAmount = 0;
        //实际价格也为0
        Integer payAmount = 0;
        for(String itemId : idArr){
            //根据子表id查询具体的信息
            ItemsSpec item = itemService.queryItemSpecById(itemId);
            // TODO  redis 购物车数量
            int buyCount = 1;
            totalAmount += item.getPriceNormal() * buyCount;
            payAmount += item.getPriceDiscount() * buyCount;

            String id = item.getItemId();
            Items items = itemService.queryItemById(id);
            String imgUrl = itemService.queryItemMainImg(id);
            //把子订单循环设置
            String subOrderId = sid.nextShort();
            OrderItems subOrder = new OrderItems();
            subOrder.setId(subOrderId);
            subOrder.setOrderId(orderId);
            subOrder.setItemName(items.getItemName());
            subOrder.setItemImg(imgUrl);
            subOrder.setBuyCounts(buyCount);

        }


    }
}
