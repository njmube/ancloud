package org.ancloud.fw.presentation.authentication;

import javax.inject.Inject;

import org.ancloud.service.account.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Inject
	private LoginAttemptService loginAttemptService;

	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
		loginAttemptService.loginSucceeded(auth.getRemoteAddress(),auth.getSessionId());
	}
}