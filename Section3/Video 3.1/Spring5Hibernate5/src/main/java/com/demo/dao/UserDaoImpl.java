package com.demo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public User findOne(int id) {
		return null;
	}
	
	@Override
	public List<User> findAll() {
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
	    return query.getResultList();
	}
	
	@Override
	public void update(User user) {
	}
	
	@Override
	public void delete(int id) {
	}
	
	/*public void createTable(){
		String query= "create table IF NOT EXISTS users(userId int not null, username varchar(10) not null ,phone varchar(10) null, PRIMARY KEY (userId))";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(query);
	}*/
}
