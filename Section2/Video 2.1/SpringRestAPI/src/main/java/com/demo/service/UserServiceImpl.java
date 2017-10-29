package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDaoImpl;
import com.demo.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDaoImpl userDao;

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}
	
	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		return userDao.findOne(id);
	}
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

}
