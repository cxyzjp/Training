package com.fih.pojo;

import java.io.Serializable;
import java.util.Date;

public class KafkaBean implements Serializable{
	private static final long serialVersionUID = 1728651482923036346L;
	
	private int id;
	private String name;
	private Date createTime;
	
	public KafkaBean(int id, String name){
		this.id = id;
		this.name = name;
		this.createTime = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return this.id +"==="+ this.name +"==="+ this.createTime;
	}
	
}
