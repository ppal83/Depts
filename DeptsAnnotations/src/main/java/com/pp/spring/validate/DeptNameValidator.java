package com.pp.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pp.spring.service.DeptService;

public class DeptNameValidator implements ConstraintValidator<UniqueDeptName, String>{
	
	@Autowired(required=true)
	@Qualifier(value="deptService")
	private DeptService deptService;
	
	@Override
	public void initialize(UniqueDeptName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if(deptService == null) return true;
		return deptService.findByName(name) == null;
	}
}
