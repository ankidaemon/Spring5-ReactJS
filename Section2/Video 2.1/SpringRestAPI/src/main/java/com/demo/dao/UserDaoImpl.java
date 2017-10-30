package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.demo.model.CustomRowMapper;
import com.demo.model.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private DriverManagerDataSource dataSource;
	
	@Autowired
	private CustomRowMapper customRowMapper;

	@Override
	public void save(User user) {
		String queryUser = "insert into users (userId, userName) values (?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(queryUser, new Object[] { user.getUserId(),user.getUserName() });
	}
	
	@Override
	public User findOne(int id) {
		String queryUser = "select * from users where userId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try{
		return (User) jdbcTemplate.queryForObject(queryUser, new Object[] { id },customRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	@Override
	public List<User> findAll() {
		createTable();
		String queryUser = "select * from users";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> users =jdbcTemplate.query(queryUser,new BeanPropertyRowMapper(User.class));
		return users;
	}
	
	@Override
	public void update(User user) {
		String queryUser = "update users set userName=? where userId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(queryUser, new Object[] { user.getUserName(),user.getUserId() });
	}
	
	@Override
	public void delete(int id) {
		String queryUser = "delete from users where userId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(queryUser, new Object[] { id });
	}
	
	public void createTable(){
		String query= "create table IF NOT EXISTS users(userId int not null, username varchar(10) not null ,phone varchar(10) null, PRIMARY KEY (userId))";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(query);
	}
}
