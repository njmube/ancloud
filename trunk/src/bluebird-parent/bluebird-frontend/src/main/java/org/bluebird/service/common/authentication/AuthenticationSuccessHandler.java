package org.bluebird.service.common.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bluebird.domain.entity.Permission;
import org.bluebird.domain.repository.AccountRepository;
import org.bluebird.domain.repository.PermissionRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//@Component("authSuccessHandlerEx")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Inject
	AccountRepository accountRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, intercepAuthentication(authentication));
	}

	private Authentication intercepAuthentication(Authentication authentication) {
		SecurityContextImpl context = new SecurityContextImpl();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Set<Permission> permissions = accountRepository.findByUserName(userDetails.getUsername()).getPermissions();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Permission permission : permissions) {
			GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermissionCode());
			authorities.add(authority);
		}
		Authentication authenticationNew = new UsernamePasswordAuthenticationToken(userDetails, UUID.randomUUID(), authorities);
		context.setAuthentication(authenticationNew);
		SecurityContextHolder.setContext(context);
		
		return authenticationNew;
	}
	
	
}
