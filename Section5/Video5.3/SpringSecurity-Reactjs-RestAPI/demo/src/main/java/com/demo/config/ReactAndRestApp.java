package com.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.demo.web","com.demo.service","com.demo.config"})
@EntityScan("com.demo.model")
@EnableJpaRepositories("com.demo.repository")
public class ReactAndRestApp {

	public static void main(String[] args) {
		SpringApplication.run(ReactAndRestApp.class, args);
	}
	
}
