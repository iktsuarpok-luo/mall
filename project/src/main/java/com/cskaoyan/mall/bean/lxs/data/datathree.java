package com.cskaoyan.mall.bean.lxs.data;

import java.util.Arrays;


public class datathree<T> {
    T[] children;
    String id;
    String label;

    public T[] getChildren() {
        return children;
    }

    public void setChildren(T[] children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "datathree{" +
                "children=" + Arrays.toString(children) +
                ", id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
