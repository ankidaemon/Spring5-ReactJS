package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.demo")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		// TODO Auto-generated method stub
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// forward static resources request to servlet container
		configurer.enable();
	}
	
	@Bean
	RequestMappingHandlerMapping requestMappingHandlerMapping(){
		RequestMappingHandlerMapping reqHandlerMapping=new RequestMappingHandlerMapping();
		reqHandlerMapping.setInterceptors(customHandlerInterceptor());
		return reqHandlerMapping;
	}

	@Bean
	public CustomHandlerInterceptor customHandlerInterceptor() {
		// TODO Auto-generated method stub
		return new CustomHandlerInterceptor();
	}
}
