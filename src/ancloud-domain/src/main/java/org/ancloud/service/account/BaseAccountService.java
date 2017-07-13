package org.ancloud.service.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AuthenticationAccountActivity;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.account.Permission;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.domain.constant.SystemCodeConstant;
import org.ancloud.domain.constant.SystemConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.core.helper.GenerationHelper;
import org.ancloud.repository.jpa.AccountRepository;
import org.ancloud.repository.jpa.AuthenticationAccountActivityRepository;
import org.ancloud.repository.jpa.RoleRepository;
import org.ancloud.service.BaseService;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class BaseAccountService<T extends Account> extends BaseService<T> implements AccountService<T>{

	@Inject 
	protected AccountRepository accountRepository;

	@Inject
	protected RoleRepository roleRepository;
	
	@Inject
	protected PasswordEncoder passwordEncoder;
	
	@Inject
	AuthenticationAccountActivityRepository authenticationAccountActivityRepository;
	
	@Override
	public T register(T account){
		T newAccount = null;
		this.setCommonBeforeRegister(account);
		newAccount = accountRepository.save(account);
		return newAccount;
	}
	
	@Override
	public T modify(T account) {
		T existingAccount = this.checkExistence(account);
		this.checkConsistency(account, existingAccount);
		
		existingAccount.setName(account.getName());
		existingAccount.setAccountStatus(account.getAccountStatus());
		existingAccount.setUserName(account.getUserName());
		existingAccount.setEmail(account.getEmail());
		return accountRepository.save(existingAccount);
	}
	
	@Override
	public void delete(T account) {
		Account existingAccount = this.checkExistence(account);
		switch(existingAccount.getAccountType()){
			default:
				accountRepository.delete(account.getId());
				break;
			}
		this.deleteById(account.getId());
	}
	
	@Override
	public T findByUserName(String userName) {
		T existingAccount = (T) accountRepository.findByUserName(userName);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		return existingAccount;
	}
	
	@Override
	public T findByIdAndAccountType(Long accountId,AccountType accountType) {
		T account = null;
		switch(accountType){
		default:
			account = (T) accountRepository.findOne(accountId);
			break;
		
		}
		if(account==null){
			throw new BusinessException("This entity didn't exist.");
		}
		return (T) account;
	}
	
	private Account validateBeforeApproval(Account account) {
		Account existingAccount = accountRepository.findOne(account.getId());
		
		if(existingAccount==null){
			throw new BusinessException("This user didn't exist.");
		}
		
		if(account.getVersion()!=existingAccount.getVersion()){
			throw new BusinessException("User's data was old. Try to fetch new data before make approval.");
		}
		
		if(existingAccount.getAccountStatus()!=AccountStatus.Pending){
			throw new BusinessException("This user already have approval.");
		}
		
		return existingAccount;
	}
	
	@Override
	public void approveAccount(T account,AccountStatus status) {
		Account existingAccount = this.validateBeforeApproval(account);
		
		existingAccount.setAccountStatus(status);
		accountRepository.save(existingAccount);
	}
	
	@Override
	@Transactional
	public void registerAuthenticationActivity(T account, AuthenticationType loginsuccess, String userAgent) {
		Account existingAccount = accountRepository.findByUserName(account.getUserName());
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		authenticationAccountActivityRepository.save(new AuthenticationAccountActivity(existingAccount,AuthenticationType.LogInSuccess,userAgent));
	}

	@Override
	public T disableAccount(String principal) {
		Account existingAccount = accountRepository.findByUserName(principal);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist anymore.");
		}
		existingAccount.setAccountStatus(AccountStatus.Disabled);
		return (T) accountRepository.save(existingAccount);
	}

	

	@Override
	public T enableAccount(String userName) {
		Account existingAccount = accountRepository.findByUserName(userName);
		if(existingAccount == null){
			throw new BusinessException("Account doesn't exist.");
		}
		existingAccount.setAccountStatus(AccountStatus.Enabled);
		return (T) accountRepository.save(existingAccount);
	}

	@Override
	public UserDetailsImpl loadUserByUserName(String username) {
		Account userAccount = accountRepository.findByUserName(username);
		return loadUser(userAccount);
	}
	
	@Override
	public UserDetailsImpl loadUserById(Long id) {
		Account userAccount = accountRepository.findOne(id);
		return loadUser(userAccount);
	}

	private UserDetailsImpl loadUser(Account userAccount) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserDetailsImpl userDetails;
		if (userAccount == null) {
			throw new UsernameNotFoundException(SystemCodeConstant.ERR_SYS_LOGIN_USERNOTFOUND); 
		}
		
		Set<Permission> permissions = userAccount.getPermissions();
		if(userAccount.getRoles()!=null) {
			for (Role role : userAccount.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
				permissions.addAll(role.getPermissions());
			}
		}
		if(permissions!=null) {
			for (Permission permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority(permission.getCode());
				authorities.add(authority);
			}
		}
		userDetails = new UserDetailsImpl(userAccount,authorities);
		
		return userDetails;
	}
	
	protected String getAccountDefaultPassword() {
		return "12345";
	}
	
	protected void setCommonBeforeRegister(T account) {
		if(StringUtils.isEmpty(account.getPassword())){
			account.setPassword(passwordEncoder.encode(SystemConstant.DEFAULT_PASSWORD));
		} else {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
		}
		if(StringUtils.isEmpty(account.getUserName())){
			account.setUserName(GenerationHelper.generateUserName());
		}
		account.setAccountNonExpired(true);
		account.setAccountNonLocked(true);
		account.setCredentialsNonExpired(true);
		account.setAccountStatus(AccountStatus.Enabled);
		String roleCode = null;
//		if(account instanceof Doctor){
//		} else {
			roleCode = "Administrator";
//		}
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findOneByCodeIgnoreCase(roleCode);
		if (role != null) {
			roles.add(role);
			account.setRoles(roles);
		}
	}

	@SuppressWarnings("unchecked")
	protected<T extends Account> T checkExistence(T account) {
		T existingAccount = null;
//		if(account instanceof Doctor){
//		} else {
			existingAccount = (T) accountRepository.findOne(account.getId());
//		}
		if(existingAccount==null){
			throw new BusinessException("This entity didn't exist.");
		}
		return existingAccount;
	}
	
	protected void checkConsistency(Account account, Account existingAccount) {
		if(account.getVersion()!=existingAccount.getVersion()){
			throw new BusinessException("There is another one modifying this. Try to fetch new data and make another submission.");
		}
	}
	
}
