package com.demo.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.*;

/**
 * @author ankidaemon
 *
 */
public class WebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// application context created by DispatcherServlet by loading web components
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// root web application context created by ContextLoaderListener
		return null;
	}
	
	@Override
	protected Filter[] getServletFilters() {
		//return your filter array
		return null;
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration){
		//Do Something
	}
	
}
