package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.model.User;
import com.demo.service.UserServiceImpl;


/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserServiceImpl service;

	@RequestMapping(value = {"/create","/"},method = RequestMethod.POST)
	public ModelAndView create(User user) {
		ModelAndView mav = new ModelAndView("home");
		service.createUser(user);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(User user) {
		ModelAndView mav = new ModelAndView("home");
		service.update(user);
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findOne(@PathVariable("id") int userId) {
		ModelAndView mav = new ModelAndView("home");
		User user=service.findOne(userId);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("home");
		service.findAll();
		return mav;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("home");
		service.delete(id);
		return mav;
	}

}
