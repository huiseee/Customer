package com.yinlei.service;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.DALOAD;
import com.yinlei.bean.Customer;
import com.yinlei.dao.CustomerDao;
import com.yinlei.dao.CustomerImpl;

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

}
