package com.dev.pojo.vo;


import java.util.List;

/**
 * 商品一级详情
 */
public class LazyItemsVO {
    private Integer rootCatId;
    private String rootCatname;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<LazySubListVO> lazySubListVO;

    public Integer getRootCatId() {
        return rootCatId;
    }

    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    public String getRootCatname() {
        return rootCatname;
    }

    public void setRootCatname(String rootCatname) {
        this.rootCatname = rootCatname;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<LazySubListVO> getLazySubListVO() {
        return lazySubListVO;
    }

    public void setLazySubListVO(List<LazySubListVO> lazySubListVO) {
        this.lazySubListVO = lazySubListVO;
    }
}
