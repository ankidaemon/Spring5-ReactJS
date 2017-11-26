package com.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.demo.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ReactiveWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebClientApplication.class, args);
	}

	@Bean
	public WebClient clientBuilder() {
		return WebClient.builder().baseUrl("http://localhost:8080/Spring-Reactive/")
				//.defaultCookie("key", "value")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Bean
	public CommandLineRunner run(WebClient webClient) throws Exception {
		return args -> {
			//get(webClient);
			post(webClient);
			//put(webClient);
			//delete(webClient);
		};
	}

	private void get(WebClient webClient) {
		// TODO Auto-generated method stub
		Mono<User> result = webClient.get().uri("/{id}", 1).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(User.class);
		
		result.subscribe(System.out::println);

		/*Flux<User> fluxResult = webClient.get().uri("/").accept(MediaType.TEXT_EVENT_STREAM).retrieve()
				.bodyToFlux(User.class);
		
		fluxResult.subscribe(System.out::println);*/

		Mono<User> monoResult = webClient.get().uri("/{id}", 1).accept(MediaType.APPLICATION_JSON).retrieve()
				.onStatus(HttpStatus::is4xxClientError, (ClientResponse clientResponse) -> {
					throw new WebClientResponseException("Bad Request", 0, null, null, null, null);
				}).onStatus(HttpStatus::is5xxServerError, (ClientResponse clientResponse) -> {
					throw new WebClientResponseException("Internal Server Error", 0, null, null, null, null);
				}).bodyToMono(User.class);

		monoResult.subscribe(System.out::println);
		
		monoResult = (Mono<User>) webClient.get().uri("/{id}", 1).accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(response -> response.bodyToMono(User.class));

		Mono<ResponseEntity<User>> resultResponseEntity = (Mono<ResponseEntity<User>>) webClient.get()
				.uri("/{id}", 1).accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(response -> response.toEntity(User.class));
		
		resultResponseEntity.subscribe(System.out::println);
	}

	private void post(WebClient webClient) {
		// TODO Auto-generated method stub
		/*Mono<User> UserMono = null;

		Mono<Void> result = webClient.post().uri("/create").contentType(MediaType.APPLICATION_JSON)
				.body(UserMono, User.class).retrieve().bodyToMono(Void.class);

		Flux<User> UserFlux = null;

		result = webClient.post().uri("/create").contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(UserFlux, User.class).retrieve().bodyToMono(Void.class);*/

		User user = new User();
		user.setUserName("IronMan");
		user.setUserId(8);
		user.setPhone("1234567890");

		Mono<Void> result1 = webClient.post().uri("/create").contentType(MediaType.APPLICATION_JSON).syncBody(user)
				.retrieve().bodyToMono(Void.class);
		result1.subscribe(System.out::println);

	}

	private void delete(WebClient webClient) {
		// TODO Auto-generated method stub
		webClient.delete().uri("/delete/{id}",1).exchange().subscribe();
	}

	private void put(WebClient webClient) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserName("Joker");
		user.setUserId(1);
		user.setPhone("1234567890");
		webClient.put().uri("/update").contentType(MediaType.APPLICATION_JSON).syncBody(user)
		.retrieve().bodyToMono(User.class).subscribe(System.out::println);
	}

}
