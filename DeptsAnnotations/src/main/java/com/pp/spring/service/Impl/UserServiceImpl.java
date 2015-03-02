package com.pp.spring.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pp.spring.DAO.UserDAO;
import com.pp.spring.model.User;
import com.pp.spring.model.UserRole;
import com.pp.spring.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	@Qualifier(value="userDAO")
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(User user) {
		Set<UserRole> roles = new HashSet<UserRole>(0);
		UserRole role = new UserRole(user, "ROLE_USER");
		roles.add(role);
		user.setEnabled(true);
		user.setUserRole(roles);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		System.out.println("HASHED:" + hashedPassword);

		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public User findByUserName(String username) {
		return userDAO.findByUserName(username);
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
}
