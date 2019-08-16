package com.cskaoyan.mall.bean;

public class L1Category {
    private String label;
    private int value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public L1Category(String label, int value) {
        this.label = label;
        this.value = value;
    }
}
