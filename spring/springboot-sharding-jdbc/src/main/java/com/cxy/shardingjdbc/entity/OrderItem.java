package com.cxy.shardingjdbc.entity;

import java.util.Date;

public final class OrderItem {

    private long orderItemId;
    private long orderId;
    private Integer createTime;
    private Date updateTime;

    public OrderItem() {}

    public OrderItem(long orderId, Integer createTime, Date updateTime) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return String.format("item_id:%s, order_id: %s, createTime: %s", orderItemId, orderId, createTime);
    }
}
