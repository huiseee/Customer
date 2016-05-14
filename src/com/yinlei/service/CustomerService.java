package com.yinlei.service;

import java.util.List;

import com.yinlei.bean.Customer;
import com.yinlei.web.formbean.Page;

public interface CustomerService {
	/**
	 * ���һ���ͻ�
	 * 
	 * @param customer
	 *            Ҫ��ӵĿͻ�
	 * @return �ɹ� true ���� false
	 */
	public boolean add(Customer customer);

	/**
	 * �޸�һ���ͻ�
	 * 
	 * @param customer
	 *            Ҫ�޸ĵĿͻ�
	 * @return �ɹ� true ���� false
	 */
	public boolean update(Customer customer);

	/**
	 * ���ݿͻ�������ɾ���ͻ�
	 * 
	 * @param id
	 *            ɾ���ͻ��ı��
	 * @return �ɹ� true ���� false
	 */
	public boolean delete(String id);

	/**
	 * ��ȡ���еĿͻ�
	 * <li>�������еĿͻ�����
	 */
	@Deprecated
	public List<Customer> getAllCustomer();

	/**
	 * ���ݿͻ��ı�Ų�ѯ�ͻ�
	 * 
	 * @param id
	 *            �ͻ��ı��
	 * @return �ɹ����ش˿ͻ������򷵻�null
	 */
	public Customer findCustomerById(String id);
	
	/**
	 * ����ҳ��������ѯ��ҳ��Ҫ��ʾ������
	 * @param currentPageIndex   ��ǰҳ������
	 * @param count    Ҫ��ʾ�ľ������ݵĸ���
	 * @return
	 */
	public Page getPageList(int currentPageIndex,int count);
	

}
