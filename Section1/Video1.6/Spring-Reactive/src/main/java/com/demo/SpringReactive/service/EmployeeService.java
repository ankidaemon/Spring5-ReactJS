package com.demo.SpringReactive.service;

import java.util.UUID;

import com.demo.SpringReactive.to.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	Mono<Employee> save(Employee employee);

	Mono<Employee> update(Employee employee);

	Mono<Employee> findOne(UUID uuid);

	Mono<Boolean> delete(UUID uuid);

	Flux<Employee> findemployeeUsingPhone(String phoneNo);
}
