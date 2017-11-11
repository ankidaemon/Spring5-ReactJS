package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserDao;
import com.demo.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(int id) {
		// TODO Auto-generated method stub
		return userDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

}
