package org.ancloud.service.authentication;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.ancloud.domain.NavigationLink;
import org.ancloud.domain.Project;
import org.ancloud.domain.common.SessionConstant;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AccountProfile;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.core.util.HttpServletRequestUtil;
import org.ancloud.repository.modules.AccountRepository;
import org.ancloud.repository.modules.NavigationLinkRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Inject
	NavigationLinkRepository navigationLinkRepository;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountRepository accountRepository;
	
	@Inject
	LocaleResolver localeResolver;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, intercepAuthentication(authentication));
		Account account = ((UserDetailsImpl)authentication.getPrincipal()).getAccount();
		AccountProfile accountProfile = new AccountProfile();
		Project project = new Project();
		project.setId(0L);
		sessionService.put(SessionConstant.SESSION_CURRENT_PROJECT,project);
		sessionService.put(SessionConstant.SESSION_ACCOUNT,account);
		sessionService.put(SessionConstant.SESSION_NAVIGATION_LINKS, navigationLinkRepository.findByParentAndProject(null,project));
		if(CollectionUtils.isNotEmpty(account.getAccountProfiles())){
			accountProfile = account.getAccountProfiles().get(0);
		}
		sessionService.put(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,accountProfile);
		localeResolver.setLocale(HttpServletRequestUtil.getRequest(), null, LocaleUtils.toLocale(accountProfile.getLocale()));
	}

	private Authentication intercepAuthentication(Authentication authentication) {
		return authentication;
	}
	
}
