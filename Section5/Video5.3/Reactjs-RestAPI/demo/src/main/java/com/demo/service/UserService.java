package com.demo.service;

import com.demo.model.User;

public interface UserService {
	void createUser(User user);

	Iterable<User> findAll();

	void update(User user);

	void delete(long id);

	User findOne(long id);
}
