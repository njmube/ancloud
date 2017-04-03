package org.ancloud.service.account;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountSearchCriteria;
import org.ancloud.domain.account.AuthenticationAccountActivity;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.repository.RoleRepository;
import org.ancloud.repository.account.AccountRepository;
import org.ancloud.repository.account.AuthenticationAccountActivityRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Inject 
	AccountRepository accountRepository;

	@Inject
	RoleRepository roleRepository;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Inject
	AuthenticationAccountActivityRepository authenticationAccountActivityRepository;
	
	@Inject
	FindByIndexNameSessionRepository sessionRepository;
	
	@Override
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria,Pageable pageable) {
		return accountRepository.findAll(criteria,pageable);
	}

	@Override
	public Account registerNewAccount(Account account) {
		this.validateBeforeRegister(account);

		this.setCommonBeforeRegister(account,AccountType.Admin);
		
		accountRepository.save(account);
		return account;
	}

	@Override
	public void modifyAccount(Account account) {
		Account existingAccount = accountRepository.findOne(account.getId());
		this.validateBeforeModify(account, existingAccount);
		
		setCommonBeforeModify(account, existingAccount);
		
		accountRepository.save(existingAccount);
	}

	
	private void setCommonBeforeRegister(Account account, AccountType accountType) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setAccountNonExpired(true);
		account.setAccountNonLocked(true);
		account.setCredentialsNonExpired(true);
		account.setAccountStatus(AccountStatus.Enabled);
		String roleCode = null;
		account.setAccountType(accountType);
		switch(accountType){
		case Admin:
			roleCode = "admin";
			break;
		}
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findOneByCodeIgnoreCase(roleCode);
		if(role!=null){
			roles.add(role);
			account.setRoles(roles);
		}
	}
	
	private void setCommonBeforeModify(Account account, Account existingAccount) {
		existingAccount.setUserName(account.getUserName());
		existingAccount.setPassword(account.getPassword());
		existingAccount.setName(account.getName());
		existingAccount.setAccountStatus(account.getAccountStatus());
	}

	private void validateBeforeModify(Account account, Account existingAccount) {
		if(existingAccount==null){
			throw new BusinessException("This user didn't exist.");
		}
		if(accountRepository.countByIdNotAndUserNameIgnoreCase(account.getId(), account.getUserName())>0){
			throw new BusinessException("User with this user name already exists.");
		}
		if(accountRepository.countByIdNotAndEmail(account.getId(), account.getEmail())>0){
			throw new BusinessException("User with this email already exists.");
		}
		if(account.getVersion()!=existingAccount.getVersion()){
			throw new BusinessException("User's data was old. Try to fetch new data before modify.");
		}
	}
	
	private void validateBeforeRegister(Account account) {
		if(accountRepository.countByUserNameIgnoreCase(account.getUserName())>0){
			throw new BusinessException("User with this user name already exists.");
		}
		if(StringUtils.isNotEmpty(account.getEmail()))
		if(accountRepository.countByEmail(account.getEmail())>0){
			throw new BusinessException("User with this email already exists.");
		}
	}

	@Override
	public void approveAccount(Account account,AccountStatus status) {
		Account existingAccount = this.validateBeforeApproval(account);
		
		existingAccount.setAccountStatus(status);
		accountRepository.save(existingAccount);
	}

	private Account validateBeforeApproval(Account account) {
		Account existingAccount = accountRepository.findOne(account.getId());
		
		if(existingAccount==null){
			throw new BusinessException("This user didn't exist.");
		}
		
		if(account.getVersion()!=existingAccount.getVersion()){
			throw new BusinessException("User's data was old. Try to fetch new data before make approval.");
		}
		
		if(account.getAccountStatus()!=AccountStatus.Pending){
			throw new BusinessException("This user already have approval.");
		}
		
		return existingAccount;
	}

	@Override
	public void registerAuthenticationActivity(Account account, AuthenticationType loginsuccess, String userAgent) {
		Account existingAccount = accountRepository.findByUserName(account.getUserName());
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		authenticationAccountActivityRepository.save(new AuthenticationAccountActivity(existingAccount,AuthenticationType.LogInSuccess,userAgent));
	}

	@Override
	public Account disableAccount(String principal) {
		Account existingAccount = accountRepository.findByUserName(principal);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist anymore.");
		}
		existingAccount.setAccountStatus(AccountStatus.Disabled);
		return accountRepository.save(existingAccount);
	}

	@Override
	public Account findAccountByUserName(String userName) {
		Account existingAccount = accountRepository.findByUserName(userName);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		return existingAccount;
	}

	@Override
	public Account enableAccount(String userName) {
		Account existingAccount = accountRepository.findByUserName(userName);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		existingAccount.setAccountStatus(AccountStatus.Enabled);
		return accountRepository.save(existingAccount);
	}

	@Override
	public Map<String,? extends Session> getSessionsByUserName(String userName) {
		return sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userName);
	}

	@Override
	public Account findAccountById(Long accountId,Integer accountType) {
		Account account = null;
		switch(accountType){
		default:
			account = accountRepository.findOne(accountId);
			break;
			
		}
		if(account == null){
			throw new BusinessException("Account doesn't exist.");
		}
		return account;
	}

}
