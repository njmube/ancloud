package org.ancloud.service.authentication;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountProfile;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.constant.SessionConstant;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.helper.HttpServletRequestHelpers;
import org.ancloud.repository.account.AccountRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Inject
	SessionService sessionService;
	
	@Inject
	AccountRepository accountRepository;
	
	@Inject
	LocaleResolver localeResolver;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Account account = ((UserDetailsImpl)authentication.getPrincipal()).getAccount();
		AccountProfile accountProfile = new AccountProfile();
		sessionService.put(SessionConstant.SESSION_ACCOUNT,account);
		if(CollectionUtils.isNotEmpty(account.getAccountProfiles())){
			accountProfile = account.getAccountProfiles().get(0);
		}
		sessionService.put(SessionConstant.SESSION_CURRENT_ACCOUNT_PROFILE,accountProfile);
		localeResolver.setLocale(HttpServletRequestHelpers.getRequest(), null, LocaleUtils.toLocale(accountProfile.getLocale()));
		super.onAuthenticationSuccess(request, response, intercepAuthentication(authentication));
	}
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request,HttpServletResponse response) {
		if (isAlwaysUseDefaultTargetUrl()) {
			return this.getDefaultTargetUrl();
		}

		String targetUrl = null;
		
		Account account = sessionService.get(SessionConstant.SESSION_ACCOUNT,Account.class);
		if(account !=null){
			for(Role role :account.getRoles()){
				switch(role.getCode()){
				case "Administrator":
					targetUrl = "/account/search";
					break;
				case "Doctor":
					targetUrl = "/med/patient/search";
					break;
				case "Nurse":
					targetUrl = "/med/patient/search";
					break;
				case "Patient":
					targetUrl = "/med/patient/show-vital?patientId="+account.getId();
					break;
				}
				if (StringUtils.hasText(targetUrl)) {
					return targetUrl;
				}
			}
		}
		if (this.getTargetUrlParameter() != null) {
			targetUrl = request.getParameter(this.getTargetUrlParameter());

			if (StringUtils.hasText(targetUrl)) {
				this.logger.debug("Found targetUrlParameter in request: "
						+ targetUrl);

				return targetUrl;
			}
		}

		if (!(StringUtils.hasText(targetUrl))) {
			targetUrl = this.getDefaultTargetUrl();
			this.logger.debug("Using default Url: " + targetUrl);
		}

		return targetUrl;
	}
	
	private Authentication intercepAuthentication(Authentication authentication) {
		return authentication;
	}
	
	public void setDefaultTargetUrl(String defaultTargetUrl){
		super.setDefaultTargetUrl(defaultTargetUrl);
	}
	
	public void setAlwaysUseDefaultTargetUrl(Boolean alwaysUseDefaultTargetUrl){
		super.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
	}
}
