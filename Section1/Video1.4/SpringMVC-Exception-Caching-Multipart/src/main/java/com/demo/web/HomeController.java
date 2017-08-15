package com.demo.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.exceptionhandlers.CustomException;
import com.demo.to.Employee;

/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {
	
	@ExceptionHandler(CustomException.class)
	public String customExceptionHandler(){
		return "error";
	}

	@RequestMapping(value = { "/", "/home" }, method = { RequestMethod.GET, RequestMethod.POST })
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
	public ModelAndView getRecord(@PathVariable("employeeId") int employeeId, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("info");
		// do something to fetch employee with eid passed
		if (employeeId == 221) {
			Employee employee = new Employee();
			employee.setName("John Watson");
			employee.setAge(25);
			employee.setAddress("221B Baker Street");
			employee.setPhoneNo(9999999999L);
			mav.addObject("employee", employee);
		} else {
			//mav.addObject("error", "No Employee found having eId " + employeeId);
			//throw new RuntimeException();
			//throw new CustomException();
			throw new IllegalStateException();
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		return mav;
	}

	@PostMapping("/signUp")
	public ModelAndView signUp(@ModelAttribute Employee employee, RedirectAttributes rattr) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/info#next", true));
		// do something in database to save incoming employee
		rattr.addFlashAttribute("redirectedEmployee", employee);
		return mav;
	}

	@GetMapping("/info")
	public ModelAndView visitInfo() {
		ModelAndView mav = new ModelAndView("info");
		return mav;
	}
	
	@PostMapping("/uploadFiles")
	public ModelAndView uploadFiles(@RequestParam(value="file",required=false) MultipartFile[] files) {
		ModelAndView mav = new ModelAndView("home");
		String uploadFolder = "C:\\Users\\Public\\Documents\\";
		File folder = new File(uploadFolder);
		if (!folder.exists()) {
			mav.addObject("error","Upload dir not found.");
			return mav;
		}
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(uploadFolder + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mav.addObject("uploadResult","File Uploaded Successfully.");
		return mav;
	}

}
