package org.ancloud.wapi.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ancloud.fw.core.exception.ErrorMessage;
import org.ancloud.fw.core.util.ErrorHandlingUtils;
import org.ancloud.fw.presentation.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping(value="api/auth")
public class AuthenticationController{
private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception ex){
		return ErrorHandlingUtils.logException(logger,ex);
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String auth(HttpSession session,Principal principal) {
		return session.getId();
	}
	@RequestMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	public class AuthenticationToken{
		
		@JsonProperty("X-Auth-Token")
		private String XAuthToken;

		public AuthenticationToken(String token){
			this.XAuthToken = token;
		}
		
		public String getXAuthToken() {
			return XAuthToken;
		}

		public void setXAuthToken(String xAuthToken) {
			XAuthToken = xAuthToken;
		}
	}
}
