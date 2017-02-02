package org.ancloud.wapi.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.ancloud.domain.AuthenticationToken;
import org.ancloud.domain.common.SessionConstant;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AccountProfile;
import org.ancloud.domain.modules.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.validation.ValidationResponseEntityBuilder;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.ancloud.service.modules.account.AccountService;
import org.ancloud.service.modules.account.LicenseService;
import org.ancloud.wapi.form.AccountStatusForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/auth")
public class AuthenticationController extends BaseController {
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	AccountService accountService;
	
	@Inject
	LicenseService licenseService;

//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ResponseBody
//	public ErrorMessage handleException(Exception ex){
//		return ErrorHandlingUtils.logException(logger,ex);
//	}
	
	@RequestMapping(method = {RequestMethod.GET})
	public ResponseEntity<?> auth(HttpSession session,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			Device device,HttpServletRequest  request,Authentication authentication) {
		if(!authentication.isAuthenticated()) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				
		Account account = userDetails.getAccount();
		AccountProfile accountProfile = new AccountProfile();
		if(CollectionUtils.isNotEmpty(account.getAccountProfiles())){
			accountProfile = account.getAccountProfiles().get(0);
		}
		accountService.registerAuthenticationActivity(account,AuthenticationType.LogInSuccess,(String)request.getHeader("user-agent"));
		
		sessionService.put(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,accountProfile);
		sessionService.put(SessionConstant.SESSION_CURRENT_DEVICE,accountProfile);
		return ResponseEntity.ok(new AuthenticationToken(session.getId()));
	}
	
	@RequestMapping(value="/check-license/{token}",method = {RequestMethod.GET})
	public ResponseEntity<?> checkLicense(@PathVariable("token") String authenticationToken) {
		return ResponseEntity.ok(licenseService.getLicense(authenticationToken));
	}
	
	@RequestMapping(value="/change-status/{userName}/{isEnabled}",method = {RequestMethod.PATCH})
	@PreAuthorize("hasRole('administrator')")
	public ResponseEntity<?> changeAccountStatus(@PathVariable("userName") String userName,@PathVariable("isEnabled") Boolean isEnabled,@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok(accountService.disableAccount(userDetails.getUsername()));
	}
	
	@RequestMapping(value="/disable",method = {RequestMethod.PATCH})
	public ResponseEntity<?> disableAccount(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok(accountService.disableAccount(userDetails.getUsername()));
	}
	
	@RequestMapping(value="/get-status/{userName}",method = {RequestMethod.GET})
	public ResponseEntity<?> getAccountStatus(@PathVariable("userName") String userName,@PathVariable("isEnabled") Boolean isEnabled) {
		
		return ResponseEntity.ok(mapper.map(accountService.findAccountByUserName(userName),AccountStatusForm.class));
	}
	
	@RequestMapping(value="get-server-time-joda",method = {RequestMethod.GET})
	public ResponseEntity<?> getJodaServerTime() {
		return ResponseEntity.ok(new DateTime());
	}
	
	@RequestMapping(value="/logout",method = {RequestMethod.PATCH})
	public ResponseEntity<?> logout(HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="get-server-time",method = {RequestMethod.GET})
	public ResponseEntity<?> getServerTime() {
		return ResponseEntity.ok(new Date());
	}
	
	
}
