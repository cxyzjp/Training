package com.cxy.sphere.entity;

public final class User {

    private int userId;

    private Integer createTime;

    public User() {}

    public User(int userId, Integer createTime) {
        this.userId = userId;
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}
