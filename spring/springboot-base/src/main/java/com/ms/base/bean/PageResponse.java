package com.ms.base.bean;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 
 * @ClassName PageResponse
 * @Description a light pageInfo
 * @author Alan Qin
 * @Date 2016年7月14日 下午4:34:42
 * @version 1.0.0
 * @param <T>
 */
public class PageResponse<T> implements Serializable {
	/**
	 * @Field @serialVersionUID
	 */
	private static final long serialVersionUID = 1872588349398885729L;
	private long total; // 总记录数
	private List<T> list; // 结果集
	private int pageNum; // 第几页
	private int pageSize; // 每页记录数
	private int pages; // 总页数
	private int size; // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
	
    public PageResponse(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
        }
    }

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
