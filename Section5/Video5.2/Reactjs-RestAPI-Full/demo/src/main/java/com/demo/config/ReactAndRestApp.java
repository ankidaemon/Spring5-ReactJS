package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.model.User;
import com.demo.repository.UserRepository;

@SpringBootApplication
@ComponentScan({"com.demo.web","com.demo.service"})
@EntityScan("com.demo.model")
@EnableJpaRepositories("com.demo.repository")
public class ReactAndRestApp {

	public static void main(String[] args) {
		SpringApplication.run(ReactAndRestApp.class, args);
	}
	
	@Bean
	@Autowired
	public CommandLineRunner run(UserRepository userRepo) throws Exception {
		return args -> {
			userRepo.save(new User("test1","9876543210"));
		};
	}
}
