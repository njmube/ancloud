package org.ancloud.wui.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountProfile;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.constant.SessionConstant;
import org.ancloud.fw.core.exception.ErrorMessage;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.core.util.ErrorHandlingUtils;
import org.ancloud.service.account.AccountService;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/auth")
public class AuthenticationController{
	
	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Inject
	private SessionService sessionService;
	
	@Inject
	AccountService accountService;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception ex){
		return ErrorHandlingUtils.logException(logger,ex);
	}
	
	@RequestMapping(method = {RequestMethod.GET})
	public ResponseEntity<?> auth(HttpSession session,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			Device device,HttpServletRequest  request) {
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
	
	@RequestMapping(value="get-server-time-joda",method = {RequestMethod.GET})
	public ResponseEntity<?> getJodaServerTime() {
		return ResponseEntity.ok(new DateTime());
	}
	
	@RequestMapping(value="get-server-time",method = {RequestMethod.GET})
	public ResponseEntity<?> getServerTime() {
		return ResponseEntity.ok(new Date());
	}
	
	@RequestMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	public class AuthenticationToken{
		
		private String token;

		public AuthenticationToken(String token){
			this.token = token;
		}
		
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}
