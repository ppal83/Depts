package com.pp.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pp.spring.service.UserService;

public class UserNameValidator implements ConstraintValidator<UniqueUserName, String>{
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	private UserService userService;

	@Override
	public void initialize(UniqueUserName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userService == null) return true;
		return userService.findByUserName(username) == null;
	}

}