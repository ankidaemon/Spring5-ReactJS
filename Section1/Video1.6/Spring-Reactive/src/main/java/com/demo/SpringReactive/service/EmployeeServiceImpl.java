package com.demo.SpringReactive.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.demo.SpringReactive.repo.EmployeeRepository;
import com.demo.SpringReactive.to.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Mono<Employee> save(Employee employee) {
		if (employee.getId() == null) {
			employee.setId(UUID.randomUUID());
		}
		Mono<Employee> saved = this.employeeRepository.save(employee);
		return saved;
	}

	@Override
	public Mono<Employee> update(Employee employee) {
		return this.employeeRepository.findOne(employee.getId())
				.flatMap(existingemployee -> this.employeeRepository.update(employee));
	}

	@Override
	public Mono<Employee> findOne(UUID uuid) {
		return this.employeeRepository.findOne(uuid);
	}

	@Override
	public Mono<Boolean> delete(UUID uuid) {
		Mono<Employee> employeeMono = this.employeeRepository.findOne(uuid);
		return employeeMono.flatMap((Employee employee) -> this.employeeRepository.delete(employee.getId()));
	}

	@Override
	public Flux<Employee> findemployeeUsingPhone(String phoneNo) {
		return this.employeeRepository.findByPhoneNumber(phoneNo);
	}

}
