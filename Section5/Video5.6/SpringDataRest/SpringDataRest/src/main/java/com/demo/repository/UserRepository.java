package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
