package com.demo.SpringReactive.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.demo.SpringReactive.to.Employee;

public interface EmployeeRepository  {
    Mono<Employee> save(Employee employee);
    Mono<Employee> update(Employee employee);
    Mono<Employee> findOne(UUID employeeId);
    Mono<Boolean> delete(UUID employeeId);
    Flux<Employee> findByPhoneNumber(String phoneNo);
}