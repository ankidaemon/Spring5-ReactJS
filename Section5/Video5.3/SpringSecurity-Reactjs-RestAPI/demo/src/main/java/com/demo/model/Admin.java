package com.demo.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ankidaemon
 *
 */
@Entity
@Table(name="AdminUser")
public class Admin {
	
	private @Id @GeneratedValue Long id;
	private String userName;
	private String password;
	private String roles;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String userName, String password, String roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
