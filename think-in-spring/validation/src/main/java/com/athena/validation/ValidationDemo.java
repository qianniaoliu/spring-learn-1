package com.athena.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

/**
 * @author yusheng
 */
public class ValidationDemo {

    public static void main(String[] args) {
        User user = new User();
        Validator validator = new LocalValidatorFactoryBean();
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        List<ObjectError> objectErrorList = errors.getAllErrors();
        System.out.println(objectErrorList);
    }
}
