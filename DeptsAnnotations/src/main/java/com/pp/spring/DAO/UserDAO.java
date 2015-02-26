package com.pp.spring.DAO;

import com.pp.spring.model.User;

public interface UserDAO {

	void addUser(User user);
	User findByUserName(String username);
	User findByEmail(String email);
}


