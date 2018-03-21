package com.cxy.shardingjdbc.entity;

import java.util.Date;

public class OrderQuery {

    private long orderId;
    private int buyer;
    private int seller;
    private Integer cStart;
    private Integer cEnd;
    private Date uStart;
    private Date uEnd;

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

    public Integer getcStart() {
        return cStart;
    }

    public void setcStart(Integer cStart) {
        this.cStart = cStart;
    }

    public Integer getcEnd() {
        return cEnd;
    }

    public void setcEnd(Integer cEnd) {
        this.cEnd = cEnd;
    }

    public Date getuStart() {
        return uStart;
    }

    public void setuStart(Date uStart) {
        this.uStart = uStart;
    }

    public Date getuEnd() {
        return uEnd;
    }

    public void setuEnd(Date uEnd) {
        this.uEnd = uEnd;
    }
}
