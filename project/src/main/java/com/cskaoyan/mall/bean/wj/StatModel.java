package com.cskaoyan.mall.bean.wj;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wj
 * @Date 2019/8/16 20:15
 */
public class StatModel {
    String[] columns;
    List<Map> rows;

    public StatModel() {
    }

    public StatModel(String[] columns, List<Map> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Map> getRows() {
        return rows;
    }

    public void setRows(List<Map> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "StatModel{" +
                "columns=" + Arrays.toString(columns) +
                ", rows=" + rows +
                '}';
    }
}

