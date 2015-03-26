package com.pp.spring.validate;

import com.pp.spring.model.User;
import com.pp.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (userService.findByUserName( ((User)target).getUsername()) != null ) {
            errors.rejectValue("username", "existingUserName", new Object[]{"'username'"},
                    "User with this name already exists");
        }
        if (userService.findByEmail(((User) target).getEmail()) != null ) {
            errors.rejectValue("email", "existingUserEmail", new Object[]{"'email'"},
                    "User with this email already exists");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userEmail.required");
    }
}