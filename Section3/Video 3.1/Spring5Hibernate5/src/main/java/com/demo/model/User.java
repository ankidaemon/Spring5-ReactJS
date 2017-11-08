package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Entity
@Table(name = "User")
@Proxy(lazy=false)
public class User {

	@Column(name = "USER_NAME")
	@Size(max = 20, min = 3, message = "Name length allowed: Max=20, Min=3")
	@NotEmpty(message = "Please Enter your name")
	String userName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	int userId;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotEmpty(message = "Please number can't be empty.")
	String phone;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
