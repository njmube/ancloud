package org.ancloud.presentation.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountLicenseMFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountLicenseForm.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//ValidationUtils.rejectIfEmpty(errors, "reenterPassword", "Confirm password must match with password.");
		if(target!=null){
			AccountLicenseForm accountLicenseRForm = (AccountLicenseForm)target;
			ValidationUtils.rejectIfEmpty(errors, "fromDate","AccountLicenseRFormValidator.NotEmpty.fromDate", "Start date must not be empty.");
			ValidationUtils.rejectIfEmpty(errors, "account.id","AccountLicenseRFormValidator.NotEmpty.account_id", "Account must not be empty.");
			if(!(accountLicenseRForm.getToDate()==null 
					|| accountLicenseRForm.getFromDate()==null 
					|| !accountLicenseRForm.getToDate().isBefore(accountLicenseRForm.getFromDate()))){
				errors.rejectValue("toDate","AccountLicenseRFormValidator.DateRange.toDate",  "End date must greater than start date.");
			}
		}
	}
}
