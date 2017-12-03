package com.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	@Query(value="select * from admin_user where user_name=:userName",nativeQuery = true)
	Admin findByUserName(@Param("userName") String userName);

}
