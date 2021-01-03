package com.dev.enums;

/**
 * @Description 订单状态的枚举
 */
public enum OrderStatus {
    WAIT_PAY(1,"等待付款"),
    WAIT_DELIVER(2,"已付款，待发货"),
    WAIT_RECEIVE(3,"已发货，待收货"),
    SUCCESS(4,"订单完成"),
    CLOSE(5,"订单关闭");

    public final Integer type;
    public final String value;

    OrderStatus(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
