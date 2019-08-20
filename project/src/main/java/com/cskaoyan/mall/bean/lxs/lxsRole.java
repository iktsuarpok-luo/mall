package com.cskaoyan.mall.bean.lxs;

public class lxsRole {
    int value;
    String label;

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

    @Override
    public String toString() {
        return "lxsRole{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }
}
