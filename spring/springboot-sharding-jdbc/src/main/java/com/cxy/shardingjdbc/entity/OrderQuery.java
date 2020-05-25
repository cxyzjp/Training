package com.cxy.shardingjdbc.entity;

import java.util.Date;

public class OrderQuery {

    private Long orderId;
    private Integer buyer;
    private Integer seller;
    private Integer cStart;
    private Integer cEnd;
    private Integer createTime;
    private Date uStart;
    private Date uEnd;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getBuyer() {
        return buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
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
