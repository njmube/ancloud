package org.ancloud.presentation.form.validator;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.ancloud.presentation.form.AccountForm;
import org.ancloud.repository.jpa.AccountRepository;
import org.springframework.validation.Errors;

public class BaseAccountValidator {
	
	@Inject 
	protected AccountRepository accountRepository;
	
	protected void checkEmailModification(AccountForm account,Errors errors) {
		if(accountRepository.countByIdNotAndEmail(account.getId(), account.getEmail())>0){
			errors.rejectValue("email",null,"This email already exists.");
		}
	}

	protected void checkUserNameModification(AccountForm account,Errors errors) {
		if(accountRepository.countByIdNotAndUserNameIgnoreCase(account.getId(), account.getUserName())>0){
			errors.rejectValue("userName",null,"User name already exists.");
		}
	}
	
	protected void checkUserNameRegistration(AccountForm account,Errors errors) {
		if(accountRepository.countByUserNameIgnoreCase(account.getUserName())>0){
			errors.rejectValue("userName",null,"User name already exists.");
		}
	}

	protected void checkEmailRegistration(AccountForm account,Errors errors) {
		if(StringUtils.isNotEmpty(account.getEmail()))
		if(accountRepository.countByEmail(account.getEmail())>0){
			errors.rejectValue("email",null,"This email already exists.");
		}
	}
}
