package com.cxy.shardingjdbc.entity.seller;

import java.util.Date;

public class Seller {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商家编号
     */
    private Long sellerSn;

    /**
     * 卖家姓名
     */
    private String realName;

    /**
     * 店铺编号
     */
    private Long storeSn;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态，0：禁用，1：启用
     */
    private Byte status;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除，0：否，1：是
     */
    private Boolean deleted;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商家编号
     *
     * @return seller_sn - 商家编号
     */
    public Long getSellerSn() {
        return sellerSn;
    }

    /**
     * 设置商家编号
     *
     * @param sellerSn 商家编号
     */
    public void setSellerSn(Long sellerSn) {
        this.sellerSn = sellerSn;
    }

    /**
     * 获取卖家姓名
     *
     * @return real_name - 卖家姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置卖家姓名
     *
     * @param realName 卖家姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取店铺编号
     *
     * @return store_sn - 店铺编号
     */
    public Long getStoreSn() {
        return storeSn;
    }

    /**
     * 设置店铺编号
     *
     * @param storeSn 店铺编号
     */
    public void setStoreSn(Long storeSn) {
        this.storeSn = storeSn;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取状态，0：禁用，1：启用
     *
     * @return status - 状态，0：禁用，1：启用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态，0：禁用，1：启用
     *
     * @param status 状态，0：禁用，1：启用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否删除，0：否，1：是
     *
     * @return deleted - 是否删除，0：否，1：是
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除，0：否，1：是
     *
     * @param deleted 是否删除，0：否，1：是
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}