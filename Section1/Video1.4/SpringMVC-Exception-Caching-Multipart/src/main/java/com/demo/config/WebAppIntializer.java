package com.demo.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author ankidaemon
 *
 */
public class WebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024;

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// application context created by DispatcherServlet by loading web
		// components
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// application context created by ContextLoaderListener
		return null;
	}

	@Override
	protected Filter[] getServletFilters() {
		// return your filter array
		return null;
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// upload temp file will put here
		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
		// register a MultipartConfigElement
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
		registration.setMultipartConfig(multipartConfigElement);
	}

}
