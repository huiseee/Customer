package com.yinlei.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import com.sun.org.apache.xml.internal.security.utils.RFC2253Parser;
import com.yinlei.bean.Customer;
import com.yinlei.utils.JdbcUtils;

public class CustomerImpl implements CustomerDao {
	// 拿到连接对象
	Connection conn = JdbcUtils.getConnection();;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public boolean add(Customer customer) {

		int n = -1;
		// 创建sql语句
		String sql = "insert into customer (id,name,gender,birthday,cellphone,email,hobby,type,description) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		// 创建预处理对象
		try {
			pstmt = conn.prepareStatement(sql);
			// 指定问号的值
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getGender());
			pstmt.setDate(4, new Date(customer.getBirthday().getTime()));
			pstmt.setString(5, customer.getCellphone());
			pstmt.setString(6, customer.getEmail());
			pstmt.setString(7, customer.getHobby());
			pstmt.setString(8, customer.getType());
			pstmt.setString(9, customer.getDescription());
			// 执行sql语句
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean update(Customer customer) {
		int n = -1;
		// 创建sql语句
		String sql = "update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,hobby=?,type=?,description=? where id = ?";

		// 创建预处理对象
		try {
			pstmt = conn.prepareStatement(sql);
			// 指定问号的值

			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getGender());
			pstmt.setDate(3, new Date(customer.getBirthday().getTime()));
			pstmt.setString(4, customer.getCellphone());
			pstmt.setString(5, customer.getEmail());
			pstmt.setString(6, customer.getHobby());
			pstmt.setString(7, customer.getType());
			pstmt.setString(8, customer.getDescription());
			pstmt.setString(9, customer.getId());
			// 执行sql语句
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean delete(String id) {
		int n = -1;
		// 创建sql语句
		String sql = "delete from customer where id = ?";

		// 创建预处理对象
		try {
			pstmt = conn.prepareStatement(sql);
			// 指定问号的值

			pstmt.setString(1, id);

			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public List<Customer> getAllCustomer() {

		List<Customer> list = new ArrayList<>();
		int n = -1;
		// 创建sql语句
		String sql = "select id,name,gender,birthday,cellphone,email,hobby,type,description from customer";

		// 创建预处理对象
		try {
			pstmt = conn.prepareStatement(sql);
			// 指定问号的值

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setEmail(rs.getString("email"));
				c.setHobby(rs.getString("hobby"));
				c.setType(rs.getString("type"));
				c.setDescription(rs.getString("description"));
				list.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public Customer findCustomerById(String id) {
		int n = -1;
		// 创建sql语句
		String sql = "select name,gender,birthday,cellphone,email,hobby,type,description from customer where id='" + id
				+ "'";

		// 创建预处理对象
		try {
			pstmt = conn.prepareStatement(sql);
			// 指定问号的值

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setEmail(rs.getString("email"));
				c.setHobby(rs.getString("hobby"));
				c.setType(rs.getString("type"));
				c.setDescription(rs.getString("description"));
				return c;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return null;
	}

}
