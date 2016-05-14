package com.yinlei.web.formbean;

import java.io.Serializable;
import java.util.List;

import com.yinlei.bean.Customer;

public class Page implements Serializable {

	private int currentPageIndex; // 当前页的索引

	private int pageCount; // 总共有多少页

	private int count = 10; // 每页显示多少条数据

	private int totalDataCount;// 表中总共的数据

	private int startIndex = 1;// 页面显示的索引的起始值

	private int endIndex; // 显示页面的索引的结束索引
	
	private List<Customer> list ;   //页面要显示的所有数据的集合

	public Page(int totalCount, int count) {
		this.totalDataCount = totalCount;
		this.count = count;

		// 计算共有多少页
		pageCount = (totalCount + count - 1) / count;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String toString() {
		return "Page [currentPageIndex=" + currentPageIndex + ", pageCount=" + pageCount + ", count=" + count
				+ ", totalDataCount=" + totalDataCount + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}

}
