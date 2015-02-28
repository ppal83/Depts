package com.pp.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pp.spring.service.EmployeeService;

public class EmpNameValidator implements ConstraintValidator<UniqueEmpName, String>{
	
	@Autowired(required=true)
	@Qualifier(value="emplService")
	private EmployeeService emplService;

	@Override
	public void initialize(UniqueEmpName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if(emplService == null) return true;
		return emplService.findByName(name) == null;
	}

}