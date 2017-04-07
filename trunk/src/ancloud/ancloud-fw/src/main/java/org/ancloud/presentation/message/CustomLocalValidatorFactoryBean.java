package org.ancloud.presentation.message;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {

	@Override
	public void setValidationMessageSource(MessageSource messageSource) {
		super.setMessageInterpolator(new CustomMessageInterpolator(messageSource));
	}
	
	@Override
	protected void processConstraintViolations(Set<ConstraintViolation<Object>> violations, Errors errors) {
		super.processConstraintViolations(violations, errors);
	}
}
