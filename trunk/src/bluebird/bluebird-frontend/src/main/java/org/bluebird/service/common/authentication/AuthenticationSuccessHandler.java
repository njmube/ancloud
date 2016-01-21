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
import javax.transaction.Transactional;

import org.bluebird.authentication.UserDetailsImpl;
import org.bluebird.common.util.SessionUtil;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.module.account.Account;
import org.bluebird.domain.module.account.Permission;
import org.bluebird.domain.module.account.Role;
import org.bluebird.domain.repository.AccountRepository;
import org.bluebird.domain.repository.NavigationLinkRepository;
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

@Transactional
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, intercepAuthentication(authentication));
		Account account = ((UserDetailsImpl)authentication.getPrincipal()).getAccount();
		SessionUtil.set(SessionConstant.SESSION_ACCOUNT,account);
		SessionUtil.set(SessionConstant.SESSION_NAVIGATION_LINKS, navigationLinkRepository.findByParentAndProject(null, account.getProject()));
	}

	private Authentication intercepAuthentication(Authentication authentication) {
		return authentication;
	}
	
}
