package com.cskaoyan.mall.bean.xw;

public class XwComment {
    private String content;
    private Boolean hasPicture;
    private String[] picUrls;
    private short star;
    private Byte type;
    private int valueId;

    public String[] getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String[] picUrls) {
        this.picUrls = picUrls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }


    public short getStar() {
        return star;
    }

    public void setStar(short star) {
        this.star = star;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }
}
