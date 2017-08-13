package com.demo.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

import com.demo.web.CustomFilter;
import com.demo.web.CustomServlet;

public class CustomInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Custom Servlet Registration
		ServletRegistration.Dynamic reg = servletContext.addServlet("customServlet", CustomServlet.class);
		reg.addMapping("/customReq/**");

		// Custom Filter Registration
		FilterRegistration.Dynamic fReg = servletContext.addFilter("customFilter", CustomFilter.class);
		fReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/customReq/**");
		
		//Custom Listener Registration
		//servletContext.addListener(new ContextLoaderListener("context xml file loc"));
	}

}
