package com.dev.enums;

/**
 * @Description 是否枚举
 */
public enum Type {
    One(1,"一级"),
    Two(2,"二级"),
    Three(3,"三级");

    public final Integer type;
    public final String value;

    Type(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
