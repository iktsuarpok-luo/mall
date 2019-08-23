package com.cskaoyan.mall.bean.zt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZtCategory {

    private Integer vaule;

    private String label;

    private List<ZtCategory> children = new ArrayList<>();

    public List<ZtCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ZtCategory> children) {
        this.children = children;
    }

    public Integer getVaule() {
        return vaule;
    }

    public void setVaule(Integer vaule) {
        this.vaule = vaule;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}