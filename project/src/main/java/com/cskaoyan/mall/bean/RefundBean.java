package com.cskaoyan.mall.bean;

import java.math.BigDecimal;

public class RefundBean {
    private int orderId;
    private BigDecimal refundMoney;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}
