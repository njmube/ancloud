package org.ancloud.service.account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import org.ancloud.domain.account.AccountLicense;
import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.core.util.DataTypeUtils;
import org.ancloud.fw.core.util.GenerationHelpers;
import org.ancloud.fw.core.util.QRGenerator;
import org.ancloud.repository.BaseRepository;
import org.ancloud.repository.jpa.AccountLicenseRepository;
import org.ancloud.repository.jpa.AccountRepository;
import org.ancloud.service.BaseService;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountLicenseServiceImpl extends BaseService<AccountLicense> implements AccountLicenseService {

	public Logger logger = LoggerFactory.getLogger(AccountLicenseServiceImpl.class);
	@Inject
	AccountLicenseRepository accountLicenseRepository;
	
	@Inject
	AccountRepository accountRepository;
	
	
	@Inject
	SessionRepository sessionRepository;
	
	@Inject
	AccountService accountService;
	
	@Override
	public BaseRepository<AccountLicense> getRepository() {
		return this.accountLicenseRepository;
	}
	
	@Override
	public AccountLicense modify(AccountLicense accountLicense) {
		return null;
	}

	@Override
	public AccountLicense register(AccountLicense accountLicense) {
		UserDetailsImpl userDetails = accountService.loadUserById(accountLicense.getAccount().getId());
		ExpiringSession session = (ExpiringSession)sessionRepository.createSession();
		AccountLicense newLicense = new AccountLicense();
		newLicense.setAccount(userDetails.getAccount());
		newLicense.setCode(session.getId());
		newLicense.setFromDate(accountLicense.getFromDate());
		newLicense.setToDate(accountLicense.getToDate());
		newLicense.setQrCode(this.generateQrCode(newLicense));
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails
																,null
																,userDetails.getAuthorities());
		SecurityContext securityContext = new SecurityContextImpl();
		securityContext.setAuthentication(authRequest);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		accountLicenseRepository.save(newLicense);
		sessionRepository.save(session);
		return newLicense;
	}
	private String generateQrCode(AccountLicense accountLicense){
		String qrFileName = GenerationHelpers.generateCode(accountLicense.getAccount().getName())+"."+QRGenerator.FILETYPE_JPG;
		Path qrFilePath = Paths.get(SystemConstant.PATH_QR_CODE
									+File.separator
									+qrFileName);
		
		if(!Files.exists(qrFilePath)){
			try {
				QRGenerator.instance.writeQrCodeFile(DataTypeUtils.toJson(accountLicense),QRGenerator.FILETYPE_JPG,qrFilePath);
			} catch (IOException e) {
				logger.error("Cannot generate QR code file.",e);
				throw new BusinessException("Cannot generate QR code file.");
			}
		}
		return qrFileName;
	}
	@Override
	public List<AccountLicense> register(List<AccountLicense> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean lockLicense(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public AccountLicense findByAuthenticationToken(String authenticationToken) {
		AccountLicense license = accountLicenseRepository.findOneByCode(authenticationToken);
		if(license == null) {
			throw new BusinessException("This token don't have license.");
		}
		return license;
	}

	@Override
	public String getQrCodeFile(AccountLicense accountLicense) {
		AccountLicense license = this.checkExistence(accountLicense.getId());
		return license.getQrCode();
	}

}