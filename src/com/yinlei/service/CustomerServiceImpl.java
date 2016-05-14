package com.yinlei.service;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.DALOAD;
import com.yinlei.bean.Customer;
import com.yinlei.dao.CustomerDao;
import com.yinlei.dao.CustomerImpl;
import com.yinlei.web.formbean.Page;

import sun.net.www.content.text.plain;

public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao dao = new CustomerImpl();

	@Override
	public boolean add(Customer customer) {
		// TODO Auto-generated method stub
		return dao.add(customer);
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return dao.update(customer);
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return dao.getAllCustomer();
	}

	@Override
	public Customer findCustomerById(String id) {
		// TODO Auto-generated method stub
		return dao.findCustomerById(id);
	}

	@Override
	public Page getPageList(int currentPageIndex, int count) {
		//��ѯ���еļ�¼��
		int totalCount = dao.getTotalCount();
		Page page = new Page(totalCount, count);
		//List<Customer> list = dao.getPageList(currentPageIndex, count);
		//����ҳ����������Ҫ��ʾ�����ݣ���ѯ����Ҫ��ʾ������   
		page.setList(dao.getPageList(currentPageIndex, count));
		return page;
	}

}
