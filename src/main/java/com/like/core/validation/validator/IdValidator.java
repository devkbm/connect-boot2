package com.like.core.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.like.core.validation.annotation.Id;
import com.like.user.service.UserService;

@Component
public class IdValidator implements ConstraintValidator<Id, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(Id constraintAnnotation) {	
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userService.CheckDuplicationUser(value);
	}

	
}
