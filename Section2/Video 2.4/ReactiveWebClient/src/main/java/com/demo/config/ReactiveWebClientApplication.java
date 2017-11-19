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
			get(webClient);
			post(webClient);
			put(webClient);
			delete(webClient);
		};
	}

	private void get(WebClient webClient) {
		// TODO Auto-generated method stub
		Mono<User> result = webClient.get().uri("/Users/{id}", 1).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(User.class);

		Flux<User> fluxResult = webClient.get().uri("/Users").accept(MediaType.TEXT_EVENT_STREAM).retrieve()
				.bodyToFlux(User.class);

		Mono<User> monoResult = webClient.get().uri("/persons/{id}", 1).accept(MediaType.APPLICATION_JSON).retrieve()
				.onStatus(HttpStatus::is4xxClientError, (ClientResponse clientResponse) -> {
					throw new WebClientResponseException("Access Forbidden", 0, null, null, null, null);
				}).onStatus(HttpStatus::is5xxServerError, (ClientResponse clientResponse) -> {
					throw new WebClientResponseException("Access Forbidden", 0, null, null, null, null);
				}).bodyToMono(User.class);

		monoResult = (Mono<User>) webClient.get().uri("/persons/{id}", 1).accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(response -> response.bodyToMono(User.class));

		Mono<ResponseEntity<User>> resultResponseEntity = (Mono<ResponseEntity<User>>) webClient.get()
				.uri("/persons/{id}", 1).accept(MediaType.APPLICATION_JSON).exchange()
				.flatMap(response -> response.toEntity(User.class));
	}

	private void post(WebClient webClient) {
		// TODO Auto-generated method stub
		Mono<User> UserMono = null;

		Mono<Void> result = webClient.post().uri("/Users/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.body(UserMono, User.class).retrieve().bodyToMono(Void.class);

		Flux<User> UserFlux = null;

		result = webClient.post().uri("/Users/{id}", 1).contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(UserFlux, User.class).retrieve().bodyToMono(Void.class);

		User User = null;

		result = webClient.post().uri("/Users/{id}", 1).contentType(MediaType.APPLICATION_JSON).syncBody(User)
				.retrieve().bodyToMono(Void.class);

	}

	private void delete(WebClient webClient) {
		// TODO Auto-generated method stub

	}

	private void put(WebClient webClient) {
		// TODO Auto-generated method stub

	}

}
