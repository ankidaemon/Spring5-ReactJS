package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.to.Employee;

/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = { "/", "/home" }, method = { RequestMethod.GET })
	public ModelAndView visitHome() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@PostMapping("/info")
	public ModelAndView visitInfo(@RequestParam(name = "name", defaultValue = "Anonymous User") String name,
			@RequestParam(name = "age", required = false) String age) {
		ModelAndView mav = new ModelAndView("info");
		mav.addObject("name", name);
		if (age != null)
			mav.addObject("age", age);
		return mav;
	}

	@GetMapping("/{employeeId}")
	public ModelAndView getRecord(@PathVariable("employeeId") int employeeId) {
		ModelAndView mav = new ModelAndView("info");
		//do something to fetch employee with eid passed
		if (employeeId == 221) {
			Employee employee = new Employee();
			employee.setName("John Watson");
			employee.setAge(25);
			employee.setAddress("221B Baker Street");
			employee.setPhoneNo(9999999999L);
			mav.addObject("employee",employee);
		}else{
			mav.addObject("error","No Employee found having eId "+employeeId);
		}
		return mav;
	}
	
	@PostMapping("/signUp")
	public ModelAndView signUp(Employee employee,RedirectAttributes rattr) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/info#next",true));
		//do something in database to save incoming employee
		rattr.addFlashAttribute("redirectedEmployee", employee);
		return mav;
	}
	
	@GetMapping("/info")
	public ModelAndView visitInfo() {
		ModelAndView mav = new ModelAndView("info");
		return mav;
	}
	
	@GetMapping("/downTime")
	public ModelAndView downTime() {
		ModelAndView mav = new ModelAndView("downTime");
		return mav;
	}

}
