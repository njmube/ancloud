package org.ancloud.service.account;

import javax.inject.Inject;

import org.ancloud.domain.AuthenticationToken;
import org.ancloud.domain.account.License;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.repository.account.AccountRepository;
import org.ancloud.repository.account.LicenseRepository;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {

	@Inject
	LicenseRepository licenseRepository;
	
	@Inject
	AccountRepository accountRepository;
	
//	@Inject
//	private SessionRegistry sessionRegistry;
	
	@Inject
	FindByIndexNameSessionRepository sessionRepository;
	
	@Inject
	UserDetailsService userDetailsService;
	
	@Inject
	SessionService sessionService;
	
	@Override
	public boolean lockLicense(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public License createNewLicense(License license) {
		UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(license.getAccount().getUserName());
		ExpiringSession session = (ExpiringSession)sessionRepository.createSession();
		License newLicense = new License();
		newLicense.setAccount(userDetails.getAccount());
		newLicense.setLicenseKey(session.getId());
		newLicense.setFromDate(license.getFromDate());
		newLicense.setToDate(license.getToDate());
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails
																,null
																,userDetails.getAuthorities());
		SecurityContext securityContext = new SecurityContextImpl();
		securityContext.setAuthentication(authRequest);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		licenseRepository.save(newLicense);
		sessionRepository.save(session);
		return newLicense;
	}

	@Override
	public Object getAllSessions() {
		return sessionService.getAllSessions();
	}

	@Override
	public License getLicense(String authenticationToken) {
		return licenseRepository.findOneByLicenseKey(authenticationToken);
	}
}
