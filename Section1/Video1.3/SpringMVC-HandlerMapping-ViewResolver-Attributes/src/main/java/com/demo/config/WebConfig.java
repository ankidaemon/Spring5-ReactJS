package com.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.demo")
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public ViewResolver viewResolver() {
		// TODO Auto-generated method stub
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setOrder(4);
		return resolver;
	}

	@Bean
	public ResourceBundleViewResolver resourceBundleViewResolver() {
		ResourceBundleViewResolver res = new ResourceBundleViewResolver();
		res.setBasename("resourcebundle-views");
		res.setOrder(3);
		return res;
	}

	@Bean
	public XmlViewResolver xmlViewResolver() {
		XmlViewResolver res = new XmlViewResolver();
		Resource resource = new ClassPathResource("/views.xml");
		res.setLocation(resource);
		res.setOrder(2);
		return res;
	}

	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver cNVResolver = new ContentNegotiatingViewResolver();
		List<ViewResolver> listViewResolver = new ArrayList<ViewResolver>();
		listViewResolver.add(viewResolver());
		listViewResolver.add(resourceBundleViewResolver());
		cNVResolver.setViewResolvers(listViewResolver);

		List<View> defaultViews = new ArrayList<View>();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		defaultViews.add(jsonView);
		cNVResolver.setDefaultViews(defaultViews);

		cNVResolver.setOrder(1);
		return cNVResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// forward static resources request to servlet container
		configurer.enable();
	}

	@Autowired
	CustomHandlerInterceptor customHandlerInterceptor;
	
	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customHandlerInterceptor)
		.addPathPatterns("/info")
		.excludePathPatterns("/downTime");
	}*/

	/*@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping reqHandlerMapping = new RequestMappingHandlerMapping();
		reqHandlerMapping.setInterceptors(customHandlerInterceptor);
		return reqHandlerMapping;
	}*/

	@Bean
	public CustomHandlerInterceptor customHandlerInterceptor() {
		// TODO Auto-generated method stub
		return new CustomHandlerInterceptor();
	}
}
