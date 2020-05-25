/******************************************************************
 *
 *    Powered By .
 *
 *    Copyright (c) 
 *    http://
 *
 *    Package:     com.weshop.base.bean
 *
 *    Filename:    PageBeanQuery.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     fih01
 *
 *    @version:    1.0.0
 *
 *    Create at:   2016年7月29日 下午5:21:37
 *
 *    Revision:
 *
 *    2016年7月29日 下午5:21:37
 *        - first revision
 *
 *****************************************************************/
package com.ms.base.bean;

import java.io.Serializable;

/**
 * @ClassName PageRequest
 * @Description TODO
 * @author lihongwang
 * @Date 2016年7月29日 下午5:21:37
 * @version 1.0.0
 */
public class PageRequest implements Serializable{
	/**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7661229022075982222L;
	private int pageNum = 1; // 第几页
	private int pageSize = 10; // 每页记录数
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
