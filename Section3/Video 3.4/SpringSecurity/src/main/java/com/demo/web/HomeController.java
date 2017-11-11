package com.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.model.User;
import com.demo.service.UserService;

/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView renderHome() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@RequestMapping(value = {"/create"},method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
			attr.addFlashAttribute("user", user);
			return "redirect:/";
		}
		service.createUser(user);
		attr.addFlashAttribute("added","User with username : "+user.getUserName()+" added successfully.");
		return "redirect:/";
	}

	@RequestMapping(value = "/updatePage", method = RequestMethod.POST)
	public ModelAndView renderUpdatePage(@RequestParam(value="id") int id) {
		ModelAndView mav = new ModelAndView("update");
		mav.addObject("toUpdate",service.findOne(id));
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
		service.update(user);
		attr.addFlashAttribute("updated","User having id: "+user.getUserId()+" successfully updated.");
		return "redirect:/";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findOne(@PathVariable("id") int userId) {
		ModelAndView mav = new ModelAndView("home");
		List<User> users=new ArrayList<>();
		users.add(service.findOne(userId));
		mav.addObject("userList",users);
		return mav;
	}
	
	@PostMapping("/nameFinder")
	public ModelAndView findByUserName(@RequestParam(value="name") String userName) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("userList",service.findByUserName(userName));
		return mav;
	}
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("userList",service.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("home");
		service.delete(id);
		mav.addObject("deleted","User having id: "+id+" successfully deleted.");
		return mav;
	}
	
	@RequestMapping(value="/accessDenied", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView accessDenied() {
    	ModelAndView mav = new ModelAndView("accessDenied");
        return mav;
    }
}
