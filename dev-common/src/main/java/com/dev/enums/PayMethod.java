package com.dev.enums;

/**
 * @Description 支付方式
 */
public enum PayMethod {
    woman(1,"微信"),
    man(2,"支付宝");

    public final Integer type;
    public  final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
