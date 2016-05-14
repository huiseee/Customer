package com.yinlei.web.formbean;

import java.io.Serializable;
import java.util.List;

import com.yinlei.bean.Customer;

public class Page implements Serializable {

	private int currentPageIndex; // ��ǰҳ������

	private int pageCount; // �ܹ��ж���ҳ

	private int count = 10; // ÿҳ��ʾ����������

	private int totalDataCount;// �����ܹ�������

	private int startIndex = 1;// ҳ����ʾ����������ʼֵ

	private int endIndex; // ��ʾҳ��������Ľ�������
	
	private List<Customer> list ;   //ҳ��Ҫ��ʾ���������ݵļ���

	public Page(int totalCount, int count) {
		this.totalDataCount = totalCount;
		this.count = count;

		// ���㹲�ж���ҳ
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
