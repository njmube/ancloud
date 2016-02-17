package org.bluebird.service.authentication;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.bluebird.domain.NavigationLink;
import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.module.account.Account;
import org.bluebird.domain.module.account.AccountProfile;
import org.bluebird.fw.core.service.SessionService;
import org.bluebird.repository.AccountRepository;
import org.bluebird.repository.NavigationLinkRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountRepository accountRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, intercepAuthentication(authentication));
		Account account = ((UserDetailsImpl)authentication.getPrincipal()).getAccount();
		sessionService.put(SessionConstant.SESSION_CURRENT_PROJECT,new Project());
		sessionService.put(SessionConstant.SESSION_ACCOUNT,account);
		sessionService.put(SessionConstant.SESSION_NAVIGATION_LINKS, navigationLinkRepository.findByParentAndProject(null,account.getProject()));
		if(CollectionUtils.isNotEmpty(account.getAccountProfiles())){
			sessionService.put(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,account.getAccountProfiles().get(0));;
		} else {
			sessionService.put(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,new AccountProfile());
		}
	}

	private Authentication intercepAuthentication(Authentication authentication) {
		return authentication;
	}
	
}
