package com.demo.web;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.UserServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ankidaemon
 *
 */
@RestController
public class HomeController {

	@Autowired
	private UserServiceImpl service;

	@RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestBody User user) {
		service.createUser(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Mono<User> update(@RequestBody User user) {
		return service.update(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Mono<User>> findOne(@PathVariable("id") Integer userId) {
		Mono<User> user = service.findOne(userId);
		HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Mono<User>>(user, status);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<Object> findAll() {
		Flux<User> users = service.findAll();
		Flux<Object> interval = Flux.interval(Duration.ofSeconds(2)).zipWith(users,
				(i, user) -> "User " + i + ": " + user);
		return interval;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id).subscribe();
	}
	
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Flux<User> findByUserName(@PathVariable("username") String userName) {
		return service.findByUserName(userName);
	}

}
