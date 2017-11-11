package com.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.demo.model","com.demo.web"},excludeFilters = {@ComponentScan.Filter(
	    type = FilterType.ASSIGNABLE_TYPE,
	    value = RepositoryConfig.class)
	  })
public class WebConfig implements WebMvcConfigurer {
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
}
