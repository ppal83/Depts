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

        Employee formEmp = (Employee) target;
        Employee dbEmpByName = emplService.findByName(formEmp.getName());
        Employee dbEmpByEmail = emplService.findByEmail(formEmp.getEmail());
        Employee dbEmpById = emplService.getEmployeeById(formEmp.getId());

        if (formEmp.getId() == 0 && dbEmpByName != null) {
            errors.rejectValue("name", "existingEmpName1", new Object[]{"'name'"},
                    "Employee with this name already exists");
        }

        if ( formEmp.getId() != 0 && dbEmpByName != null
                && !dbEmpByName.getName().equals(dbEmpById.getName()) ) {
            errors.rejectValue("name", "existingEmpName2", new Object[]{"'name'"},
                    "Cannot change to existing employee name");
        }

        if (formEmp.getId() == 0 && dbEmpByEmail != null) {
            errors.rejectValue("email", "existingEmpEmail1", new Object[]{"'email'"},
                    "Employee with this email already exists");
        }

        if ( formEmp.getId() != 0 && dbEmpByEmail != null
                && !dbEmpByEmail.getName().equals(dbEmpById.getName()) ) {
            errors.rejectValue("email", "existingEmpEmail2", new Object[]{"'email'"},
                    "Cannot change to existing employee email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hireDate", "hireDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "salary.required");

    }
}
