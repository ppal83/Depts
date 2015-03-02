package com.pp.spring.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pp.spring.DAO.UserDAO;
import com.pp.spring.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		logger.info("User was saved successfully. User details: " + user);
	}
	
	public User findByUserName(String username) {
		User user = null;
		user = (User) sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).uniqueResult();
		
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = null;
		user = (User) sessionFactory.getCurrentSession()
				.createQuery("from User where email=?")
				.setParameter(0, email).uniqueResult();
		
		return user;
	}

}