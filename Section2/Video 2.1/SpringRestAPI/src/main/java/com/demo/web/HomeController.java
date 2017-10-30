package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.UserServiceImpl;


/**
 * @author ankidaemon
 *
 */
@RestController
public class HomeController {
	
	@Autowired
	private UserServiceImpl service;

	@RequestMapping(value = {"/create","/"},method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestBody User user) {
		service.createUser(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody User user) {
		service.update(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findOne(@PathVariable("id") int userId) {
		return service.findOne(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}

}
