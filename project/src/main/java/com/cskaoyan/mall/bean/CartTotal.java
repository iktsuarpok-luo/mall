package com.cskaoyan.mall.bean;

public class CartTotal {
    private long goodsCount;
    private long checkedGoodsCount;
    private double goodsAmount;
    private double checkedGoodsAmount;

    public long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public long getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public void setCheckedGoodsCount(long checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public double getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(double checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }
}
