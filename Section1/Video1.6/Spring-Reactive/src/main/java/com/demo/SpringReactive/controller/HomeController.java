package com.demo.SpringReactive.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.SpringReactive.service.EmployeeService;
import com.demo.SpringReactive.to.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/employees")
public class HomeController {

    private final EmployeeService employeeService;

    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public Mono<Employee> get(@PathVariable("id") UUID uuid) {
        return this.employeeService.findOne(uuid);
    }

    @PostMapping
    public Mono<ResponseEntity<Employee>> save(@RequestBody Employee employee) {
        return this.employeeService.save(employee)
                .map(savedemployee -> new ResponseEntity<>(savedemployee, HttpStatus.CREATED));
    }

    @PutMapping
    public Mono<ResponseEntity<Employee>> update(@RequestBody Employee employee) {
        return this.employeeService.update(employee)
                .map(savedemployee -> new ResponseEntity<>(savedemployee, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<String>> delete(
            @PathVariable("id") UUID uuid) {
        return this.employeeService.delete(uuid).map((Boolean status) ->
                new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED));
    }

    @GetMapping(path = "/{phoneNo}")
    public Flux<Employee> findEmployeeUsingPhone(
            @PathVariable("phoneNo") String phoneNo) {
        return this.employeeService.findemployeeUsingPhone(phoneNo);
    }
}
