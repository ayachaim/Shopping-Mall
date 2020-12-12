package com.dev.pojo.vo;

/**
 * 一级商品下的六个二级商品
 */
public class LazySubListVO {
    private Integer itemId;
    private String itemName;
    private String itemUrl;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
