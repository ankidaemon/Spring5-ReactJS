package com.demo.config;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CustomHandlerInterceptor extends HandlerInterceptorAdapter {
	
	int downTime=24;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar cal= Calendar.getInstance();
		if(downTime==cal.get(Calendar.HOUR_OF_DAY)){
			response.sendRedirect("/SpringMVC-DispatcherServlet-Controllers/error.html");
			return false;
		}else return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//do something
	}

}
