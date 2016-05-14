package com.yinlei.service;

import java.util.List;

import com.yinlei.bean.Customer;

public interface CustomerService {
	/**
	 * 添加一个客户
	 * 
	 * @param customer
	 *            要添加的客户
	 * @return 成功 true 否则 false
	 */
	public boolean add(Customer customer);

	/**
	 * 修改一个客户
	 * 
	 * @param customer
	 *            要修改的客户
	 * @return 成功 true 否则 false
	 */
	public boolean update(Customer customer);

	/**
	 * 根据客户的主键删除客户
	 * 
	 * @param id
	 *            删除客户的编号
	 * @return 成功 true 否则 false
	 */
	public boolean delete(String id);

	/**
	 * 获取所有的客户
	 * <li>返回所有的客户集合
	 */
	public List<Customer> getAllCustomer();

	/**
	 * 根据客户的编号查询客户
	 * 
	 * @param id
	 *            客户的编号
	 * @return 成功返回此客户，否则返回null
	 */
	public Customer findCustomerById(String id);

}
