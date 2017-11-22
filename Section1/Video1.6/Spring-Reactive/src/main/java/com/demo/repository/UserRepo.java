package com.demo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.demo.model.User;

import reactor.core.publisher.Flux;

public interface UserRepo extends ReactiveMongoRepository<User, Integer> {
	@Query("{ 'userName': ?0 }")
	Flux<User> findByUserName(final String userName);
}
