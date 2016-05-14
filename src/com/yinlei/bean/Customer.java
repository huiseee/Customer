package com.yinlei.bean;

import java.util.Date;

public class Customer {
	/**
	 * id VARCHAR(100) PRIMARY KEY,
		NAME VARCHAR(100),	
		gender VARCHAR(4),# 1 male 0 female
		birthday DATE,
		cellphone VARCHAR(20),
		email VARCHAR(40),
		hobby VARCHAR(100),#eat,sleep,study
		TYPE VARCHAR(40),#vip|normal
		description VARCHAR(255)
	 */
	
	private String id;
	
	private String name;
	
	private String gender;
	
	private Date birthday;
	
	private int cellphone;
	
	private String email;
	
	private String hobby;
	
	private String type;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Customer() {
		super();
	}

	public Customer(String id, String name, String gender, Date birthday, int cellphone, String email, String hobby,
			String type, String description) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.email = email;
		this.hobby = hobby;
		this.type = type;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday
				+ ", cellphone=" + cellphone + ", email=" + email + ", hobby=" + hobby + ", type=" + type
				+ ", description=" + description + "]";
	}
	
	
}
