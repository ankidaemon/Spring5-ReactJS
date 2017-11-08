package com.demo.service;

import java.util.List;

import com.demo.model.User;

public interface UserService {
	void createUser(User user);

	User findOne(int user);

	List<User> findAll();

	void update(User user);

	void delete(int id);
}
