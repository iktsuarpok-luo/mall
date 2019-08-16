package com.cskaoyan.mall.bean;

import java.util.List;

public class PageBean<T> {
    private List<T> rows;
    private long total;

    public PageBean(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public PageBean() {
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
