package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.User;
import com.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}
	
	@Override
	public User findOne(long id) {
		// TODO Auto-generated method stub
		return userRepo.findOne(id);
	}
	
	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
	
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		userRepo.delete(id);
	}

}
