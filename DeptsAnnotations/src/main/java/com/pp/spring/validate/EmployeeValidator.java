package com.pp.spring.validate;

import com.pp.spring.model.Employee;
import com.pp.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Autowired
    private EmployeeService emplService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Employee.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (emplService.findByName( ((Employee)target).getName()) != null ) {
            errors.rejectValue("name", "existingEmpName", new Object[]{"'name'"},
                    "Employee with this name already exists");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hireDate", "hireDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "salary.required");

    }
}
