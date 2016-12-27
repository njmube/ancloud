package org.ancloud.presentation.message;

import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {

	@Override
	public void setValidationMessageSource(MessageSource messageSource) {
		super.setMessageInterpolator(new CustomMessageInterpolator(messageSource));
	}
}
