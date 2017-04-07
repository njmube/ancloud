package org.ancloud.presentation.form.validator;

import org.apache.commons.lang3.StringUtils;
import org.ancloud.fw.core.util.DataTypeUtils;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.presentation.form.AccountRForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountRFormValidator extends BaseAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountRForm.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty(errors, "reenterPassword", "Confirm password must match with password.");
		if(target!=null){
			AccountForm accountForm = (AccountForm)target;
			if((StringUtils.isNotEmpty(accountForm.getPassword()) || StringUtils.isNotEmpty(accountForm.getReenterPassword())) 
					& DataTypeUtils.notEqual(accountForm.getPassword(), accountForm.getReenterPassword())) {
				errors.rejectValue("reenterPassword",null,"Confirm password must match with password.");
			}
			this.checkUserNameRegistration(accountForm,errors);
			this.checkEmailRegistration(accountForm,errors);
		}
	}
}
