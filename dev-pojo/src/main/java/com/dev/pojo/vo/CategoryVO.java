package com.dev.pojo.vo;

import java.util.List;


/**
 * 二级分类VO
 */
public class CategoryVO {
    private int id;
    private String name;
    private int type;
    private int father_id;

    //三级分类VO
    private List<SubListVO> subListVO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }
}
