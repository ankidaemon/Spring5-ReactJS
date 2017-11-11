package com.demo.dao;

import java.util.List;

import com.demo.model.User;

public interface UserDao {

	void save(User user);

	void update(User user);

	void delete(int id);

	User findOne(int id);

	List<User> findAll();

	List<User> findByUserName(String userName);
}
