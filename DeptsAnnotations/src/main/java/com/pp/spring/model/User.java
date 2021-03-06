package com.pp.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

import com.pp.spring.validate.UniqueUserEmail;
import com.pp.spring.validate.UniqueUserName;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Size(min=2, max=30)
	@Column(name="username")
	@UniqueUserName
	private String username;
	
	@Size(min=6)
	@Column(name="password")
	private String password;
	
	@Size(min=3, max=30)
	@Email
	@Column(name="email")
	@UniqueUserEmail
	private String email;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(mappedBy = "user")
	@Cascade(CascadeType.ALL)
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	public User() {
		
	}
 
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
 
	public User(String username, String password, 
		boolean enabled, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + ", enabled=" + enabled + "]";
	}
	
}