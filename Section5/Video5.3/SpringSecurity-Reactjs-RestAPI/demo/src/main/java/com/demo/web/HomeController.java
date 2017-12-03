package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@Secured("ROLE_ADMIN")
public class HomeController {
	
	@Autowired
	private UserServiceImpl service;

	@RequestMapping(value = {"/create"},method = RequestMethod.POST)
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
	public ResponseEntity<User> findOne(@PathVariable("id") int userId) {
		User user=service.findOne(userId);
		HttpStatus status=user!=null?HttpStatus.OK:HttpStatus.NOT_FOUND;
		return new ResponseEntity<User>(user,status);
	}
	
	@RequestMapping(value="/findAll", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<User>> findAll() {
		Iterable<User> users=service.findAll();
		HttpStatus status=users!=null?HttpStatus.OK:HttpStatus.NOT_FOUND;
		return new ResponseEntity<Iterable<User>>(users,status);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
}
