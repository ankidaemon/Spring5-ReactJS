package com.demo.config;

import org.springframework.web.reactive.support.AbstractAnnotationConfigDispatcherHandlerInitializer;

/**
 * @author ankidaemon
 *
 */
public class WebAppIntializer extends AbstractAnnotationConfigDispatcherHandlerInitializer{

	@Override
	protected Class<?>[] getConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { WebConfig.class,RootConfig.class };
	}	
}
