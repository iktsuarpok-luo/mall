package com.cskaoyan.mall.bean;

import java.util.List;

public class Check {
    private  boolean isChecked;
    private List<Integer> productIds;

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
