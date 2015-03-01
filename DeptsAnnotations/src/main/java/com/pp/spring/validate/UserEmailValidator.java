package com.pp.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pp.spring.service.UserService;

public class UserEmailValidator implements ConstraintValidator<UniqueUserEmail, String>{
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	private UserService userService;

	@Override
	public void initialize(UniqueUserEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(userService == null) return true;
		return userService.findByEmail(email) == null;
	}

}