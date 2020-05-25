package com.ms.base.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 条件对象公用对象
 * 
 * @author lx
 */
public class BaseQuery implements Serializable {
	/**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6732125234332694681L;
	// 定义常量 每页数
	public final static int DEFAULT_SIZE = 10;
	// 每页数
	protected int pageSize = DEFAULT_SIZE;
	// 页码
	protected int pageNum = 1;

	private String groupByFields;

	private List<OrderByField> orderByFields = new ArrayList<OrderByField>();

	public String getGroupByFields() {
		return groupByFields;
	}

	public void setGroupByFields(String groupByFields) {
		this.groupByFields = groupByFields;
	}

	public List<OrderByField> getOrderByFields() {
		return orderByFields;
	}

	public class OrderByField {
		public OrderByField(String fieldName, String order) {
			super();
			this.fieldName = fieldName;
			this.order = order;
		}

		private String fieldName;
		private String order;

		public String getFieldName() {
			return fieldName;
		}

		public OrderByField setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}

		public String getOrder() {
			return order;
		}

		public OrderByField setOrder(String order) {
			this.order = order;
			return this;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
