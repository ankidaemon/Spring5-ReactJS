package com.demo.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.demo.bootstrap.BootStrapUtility;
import com.demo.model.User;

/**
 * @author ankidaemon
 *
 */
public class App {
	public static void main(String[] args) {
		Session session = BootStrapUtility.getSessionFactory().openSession();
		
		User user=new User();
		user.setUserName("testUser");
		user.setPhone("1234567890");
		
		session.beginTransaction();
		
		session.save(user);
		
		Query query = session.createQuery("from User where userName = :userName");
		query.setParameter("userName", "testUser");
		final List<User> list = query.getResultList();
		System.out.println(list.toString());
		
		session.getTransaction().commit();
		session.close();
	}
}
