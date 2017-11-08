package com.demo.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.model.User;

@SpringBootApplication
public class SpringRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestTemplateApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//getMethods(restTemplate);
			//putMethod(restTemplate);
			//postMethods(restTemplate);
			//deleteMethod(restTemplate);
			miscellaneousMethods(restTemplate);
		};
	}
	
	void getMethods(RestTemplate restTemplate){
		int id=1;
		User user = restTemplate.getForObject("http://localhost:8080/SpringRestAPI/{id}", User.class,id);
		System.out.println(user.toString());
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("id", "1");
		ResponseEntity<User> response= restTemplate.getForEntity("http://localhost:8080/SpringRestAPI/{id}", User.class,urlVariables);
		if(response.getStatusCode() == HttpStatus.OK){
			System.out.println(response.getBody());
		}
	}
	
	void putMethod(RestTemplate restTemplate){
		User user=new User();
		user.setUserName("Joker");
		user.setUserId(1);
		user.setPhone("1234567890");
		String url = "http://localhost:8080/SpringRestAPI/update/";
		restTemplate.put(URI.create(url), user);
	}
	
	void postMethods(RestTemplate restTemplate){
		User user=new User();
		user.setUserId(2);
		user.setUserName("newUser");
		user.setPhone("0987654321");
		
		//restTemplate.postForObject("http://localhost:8080/SpringRestAPI/create/", user, User.class);
		URI location=restTemplate.postForLocation("http://localhost:8080/SpringRestAPI/create/", user);
		if(location!=null){
			System.out.println(location.toString());
		}
	}
	
	void deleteMethod(RestTemplate restTemplate){
		int id=2;
		restTemplate.delete("http://localhost:8080/SpringRestAPI/delete/{id}", id);
	}
	
	void miscellaneousMethods(RestTemplate restTemplate){
		MultiValueMap<String, String> headerMap =	new LinkedMultiValueMap<String, String>();
		headerMap.add("Accept", "application/json");
		headerMap.add("connection", "keep-alive");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headerMap);
		int id=1;
		ResponseEntity<User> response= restTemplate.exchange("http://localhost:8080/SpringRestAPI/{id}",HttpMethod.GET,requestEntity, User.class,id);
		System.out.println(response.getBody());
		
		HttpHeaders httpHeaders = restTemplate.headForHeaders("http://localhost:8080/SpringRestAPI/");
		System.out.println(httpHeaders.getContentType());
	}
}
