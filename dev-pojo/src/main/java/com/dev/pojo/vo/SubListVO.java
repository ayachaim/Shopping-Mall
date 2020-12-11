package com.dev.pojo.vo;

/**
 * 三级分类VO
 */
public class SubListVO {
    private int SubId;
    private String SubName;
    private int SubType;
    private int SubFatherId;

    public int getSubId() {
        return SubId;
    }

    public void setSubId(int subId) {
        SubId = subId;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public int getSubType() {
        return SubType;
    }

    public void setSubType(int subType) {
        SubType = subType;
    }

    public int getSubFatherId() {
        return SubFatherId;
    }

    public void setSubFatherId(int subFatherId) {
        SubFatherId = subFatherId;
    }
}
