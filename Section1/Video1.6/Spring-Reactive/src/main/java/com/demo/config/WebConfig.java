package com.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author ankidaemon
 *
 */
@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "com.demo")
public class WebConfig implements WebFluxConfigurer {
	
}
