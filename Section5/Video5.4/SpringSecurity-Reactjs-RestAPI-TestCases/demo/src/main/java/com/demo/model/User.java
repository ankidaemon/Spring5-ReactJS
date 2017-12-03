package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author ankidaemon
 *
 */
@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String userName;
	private String phone;
	
	public User() {
	}

	public User(String userName, String phone) {
		super();
		this.userName = userName;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
