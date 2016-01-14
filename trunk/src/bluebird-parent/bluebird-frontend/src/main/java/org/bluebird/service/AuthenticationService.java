package org.bluebird.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authenticationService")
public class AuthenticationService {

	public String getAuthenticatedUsername() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
