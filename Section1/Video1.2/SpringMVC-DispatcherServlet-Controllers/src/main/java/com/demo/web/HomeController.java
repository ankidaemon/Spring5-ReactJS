package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ankidaemon
 *
 */
@Controller
public class HomeController {
     
    @RequestMapping(value={"/","/home"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView visitHome() {
    	ModelAndView mav = new ModelAndView("home");
        return mav;
    }
    
}
