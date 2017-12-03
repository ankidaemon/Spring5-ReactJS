package com.demo.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.to.UserTo;

/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {
     
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView visitHome() {
    	ModelAndView mav = new ModelAndView("home");
        return mav;
    }
	
    @Secured("authenticated")
    @RequestMapping(value="/customlogout", method = RequestMethod.POST)
    public void logOut(){
    }
    
    @Secured("ROLE_CHIEF")
  //@PreAuthorize("hasRole('AGENT')")
    @RequestMapping(value = "/chief/updateProfile", method = RequestMethod.GET)
	public ModelAndView updateChiefPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chiefUpdate");
		return mav;
	}
    
    @Secured("ROLE_AGENT")
    @RequestMapping(value = "/agent/updateProfile", method = RequestMethod.GET)
	public ModelAndView updateUserPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userUpdate");
		return mav;
	}
    
    @PreAuthorize("principal.username=='agent'")
    @RequestMapping(value = "/withUserNameTest", method = RequestMethod.GET)
	public ModelAndView withUserNameTest(UserTo userTo) {
    	ModelAndView mav = new ModelAndView("home");
    	mav.addObject("userTo",userTo);
        return mav;
	}
    
    @RequestMapping(value="/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
    	ModelAndView mav = new ModelAndView("accessDenied");
        return mav;
    }
    
    public void withUserDetailTest() {
		// TODO Auto-generated method stub
	System.out.println("Called - withUserDetailTest");
    }
}
