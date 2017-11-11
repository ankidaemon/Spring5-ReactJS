package com.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		return user;
	}

	@Override
	public List<User> findByUserName(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName = :userName");
		query.setParameter("userName", userName);
		final List<User> list = query.list();
		return list;
	}

	@Override
	public List<User> findAll() {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
		return users;
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		if (user != null) {
			session.delete(user);
		}
	}

}
