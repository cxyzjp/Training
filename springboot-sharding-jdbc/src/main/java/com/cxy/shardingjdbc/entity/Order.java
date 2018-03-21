package com.cxy.shardingjdbc.entity;

import java.util.Date;

public class Order {

    private long orderId;
    private int buyer;
    private int seller;
    private Integer createTime;
    private Date updateTime;

    public Order() {}

    public Order(int buyer, int seller, Integer createTime, Date updateTime) {
        this.buyer = buyer;
        this.seller = seller;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
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
        return String.format("order_id: %s, buyer: %s, seller: %s", orderId, buyer, seller);
    }
}
