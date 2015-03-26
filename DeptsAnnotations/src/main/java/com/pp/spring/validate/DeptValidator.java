package com.pp.spring.validate;

import com.pp.spring.model.Dept;
import com.pp.spring.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DeptValidator implements Validator {

    @Autowired
    private DeptService deptService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Dept.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (deptService.findByName( ((Dept)target).getName()) != null ) {
            errors.rejectValue("name", "existingDeptName", new Object[]{"'name'"},
                    "Dept with this name already exists");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "deptName.required");
    }
}