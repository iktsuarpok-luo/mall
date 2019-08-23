package com.cskaoyan.mall.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckCart {
    private int isChecked;
    private ArrayList<Integer> productIds;

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public ArrayList<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(ArrayList<Integer> productIds) {
        this.productIds = productIds;
    }
}
