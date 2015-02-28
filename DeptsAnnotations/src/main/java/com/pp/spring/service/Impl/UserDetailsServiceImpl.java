package com.pp.spring.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pp.spring.DAO.UserDAO;
import com.pp.spring.model.UserRole;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired(required=true)
	@Qualifier(value="userDAO")
	private UserDAO userDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
											throws UsernameNotFoundException {

		com.pp.spring.model.User user = userDAO.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
	}

	// Converts com.pp.spring.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.pp.spring.model.User user, 
											List<GrantedAuthority> authorities) {

		return new User(user.getUsername(),user.getPassword(), user.isEnabled(), 
						true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		System.out.println(Result);
		return Result;
	}
}