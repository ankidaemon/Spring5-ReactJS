package com.demo.SpringReactive.repo;

import java.util.UUID;

import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.demo.SpringReactive.to.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ReactiveEmployeeRepoImpl implements EmployeeRepository {

	private final ReactiveCassandraOperations cassandraTemplate;

	public ReactiveEmployeeRepoImpl(ReactiveCassandraOperations cassandraTemplate) {
		this.cassandraTemplate = cassandraTemplate;
	}

	@Override
	public Mono<Employee> save(Employee employee) {
		// TODO Auto-generated method stub
		return this.cassandraTemplate.insert(employee);
	}

	@Override
	public Mono<Employee> update(Employee employee) {
		// TODO Auto-generated method stub
		 return this.cassandraTemplate.update(employee);
	}

	@Override
	public Mono<Employee> findOne(UUID employeeId) {
		// TODO Auto-generated method stub
		return this.cassandraTemplate.selectOneById(employeeId, Employee.class);
	}

	@Override
	public Mono<Boolean> delete(UUID employeeId) {
		// TODO Auto-generated method stub
		return this.cassandraTemplate.deleteById(employeeId, Employee.class);
	}

	@Override
	public Flux<Employee> findByPhoneNumber(String phoneNo) {
		// TODO Auto-generated method stub
		Select select = QueryBuilder.select().from("employees_by_phoneNumber");
        select.where(QueryBuilder.eq("phoneNumber", phoneNo));
        return this.cassandraTemplate.select(select, Employee.class);
	}

}
