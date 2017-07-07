package org.ancloud.presentation.form.validator;

import org.ancloud.fw.core.util.DataTypeUtils;
import org.ancloud.presentation.form.AccountForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountFormRValidator extends BaseAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountForm.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target!=null){
			AccountForm accountForm = (AccountForm)target;
			if(StringUtils.isEmpty(accountForm.getUserName())) {
				errors.rejectValue("userName",null,"User name mustn't be empty");
			}
			if(StringUtils.isEmpty(accountForm.getPassword())) {
				errors.rejectValue("password",null,"Password mustn't be empty");
			}
			if((StringUtils.isNotEmpty(accountForm.getPassword()) || StringUtils.isNotEmpty(accountForm.getReenterPassword())) 
					& DataTypeUtils.notEqual(accountForm.getPassword(), accountForm.getReenterPassword())) {
				errors.rejectValue("reenterPassword",null,"Confirm password must match with password.");
			}
			this.checkUserNameRegistration(accountForm,errors);
			this.checkEmailRegistration(accountForm,errors);
		}
	}
}
