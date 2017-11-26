package org.ancloud.wapi.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountLicense;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.response.ResponseWrapper;
import org.ancloud.fw.presentation.validation.ValidationResponseEntityBuilder;
import org.ancloud.presentation.form.AccountLicenseForm;
import org.ancloud.presentation.service.SessionService;
import org.ancloud.repository.mappers.AccountMapper;
import org.ancloud.service.account.AccountLicenseService;
import org.ancloud.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/account")
public class AccountController extends BaseController {
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	private AccountLicenseService licenseService;
	
	@Inject
	AccountService<Account> accountService;
	
	@Inject
	AccountMapper accountMapper;

	@RequestMapping(value="get-sessions",method = {RequestMethod.GET})
	public ResponseEntity<?> getSession(Principal principal) {
		return ResponseEntity.ok(sessionService.getSessionsByUserName(principal.getName()));
	}
	
	@RequestMapping(value="get-all-sessions",method = {RequestMethod.GET})
	@PreAuthorize("hasRole('administrator')")
	public ResponseEntity<?> getAllSessions() {
		return ResponseEntity.ok(sessionService.getAllSessions());
	}
	
	@RequestMapping(value="register-session",method = {RequestMethod.POST})
	@PreAuthorize("hasRole('administrator')")
	public ResponseEntity<?> registerSession(@RequestBody @Valid AccountLicenseForm licenseForm,BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()){
			return ValidationResponseEntityBuilder.build(logger,bindingResult.getAllErrors());
		}
		return ResponseWrapper.success(licenseService.register(mapper.map(licenseForm, AccountLicense.class)));
	}
	
	@RequestMapping(value="get-accounts",method = {RequestMethod.GET})
	@PreAuthorize("hasRole('administrator')")
    public ResponseEntity<ResponseWrapper<List<Account>>> getAccounts() {
        return ResponseWrapper.success(accountMapper.findAll());
    }
}
	