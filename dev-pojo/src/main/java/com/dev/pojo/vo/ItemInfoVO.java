package com.dev.pojo.vo;

import com.dev.pojo.Items;
import com.dev.pojo.ItemsImg;
import com.dev.pojo.ItemsParam;
import com.dev.pojo.ItemsSpec;

import java.util.List;

/**
 * 查询商品列表详情VO
 */
public class ItemInfoVO {
    private Items items;
    private List<ItemsImg> itemsImgs;
    private List<ItemsSpec> itemsSpecs;
    private ItemsParam itemParam;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<ItemsImg> getItemsImgs() {
        return itemsImgs;
    }

    public void setItemsImgs(List<ItemsImg> itemsImgs) {
        this.itemsImgs = itemsImgs;
    }

    public List<ItemsSpec> getItemsSpecs() {
        return itemsSpecs;
    }

    public void setItemsSpecs(List<ItemsSpec> itemsSpecs) {
        this.itemsSpecs = itemsSpecs;
    }

    public ItemsParam getItemParam() {
        return itemParam;
    }

    public void setItemParam(ItemsParam itemParam) {
        this.itemParam = itemParam;
    }
}
