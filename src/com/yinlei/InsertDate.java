package com.yinlei;

import java.net.URLEncoder;
import java.util.Date;

import com.yinlei.bean.Customer;
import com.yinlei.dao.CustomerDao;
import com.yinlei.dao.CustomerImpl;
import com.yinlei.utils.WebTools;



public class InsertDate {


	public static void main(String[] args) {
		
		CustomerDao dao = new CustomerImpl() ;
		
		for (int i = 0; i < 105; i++) {
			Customer c = new Customer() ;
			c.setId(WebTools.createNewId());
			c.setName("����" + (i+1)) ;
			c.setCellphone(i + 1331) ;
			c.setBirthday(new java.sql.Date(new Date().getTime())) ;
			c.setEmail("����" + i + "@i1245.cn") ;
			c.setGender("1") ;
			c.setHobby("�Է�,˯��") ;
			c.setType("vip") ;
			c.setDescription("��������") ;
			
			dao.add(c) ;
		}
	}

}
