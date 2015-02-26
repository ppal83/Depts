package com.pp.spring.service;

import com.pp.spring.model.User;

public interface UserService {
	
	void addUser(User user);
	User findByUserName(String username);
	User findByEmail(String email);

}
