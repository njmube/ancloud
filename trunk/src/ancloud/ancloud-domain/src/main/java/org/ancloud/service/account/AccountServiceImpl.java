package org.ancloud.service.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AuthenticationAccountActivity;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.account.Permission;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.domain.utils.SystemCodeConstant;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.repository.AccountRepository;
import org.ancloud.repository.AuthenticationAccountActivityRepository;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends BaseAccountService implements AccountService {

	@Inject
	AccountRepository accountRepository;
	
	@Inject
	AuthenticationAccountActivityRepository authenticationAccountActivityRepository;
	
	@Inject
	SessionService sessionService;
	
	
	@Override
	public Page<Account> findAllByCriteria(Specification<Account> criteria,Pageable pageable) {
		return accountRepository.findAll(criteria,pageable);
	}

	@SuppressWarnings("unchecked")
	public<T extends Account> T registerNew(T account){
		T newAccount = null;
		
			account.setAccountType(AccountType.Administrator);
			this.setCommonBeforeRegister(account);
			newAccount = (T) accountRepository.save(account);
		return newAccount;
	}
	
	@Override
	public Account findByIdAndAccountType(Long accountId,AccountType accountType) {
		Account account = null;
		switch(accountType){
		// TODO
		default:
			account = accountRepository.findOne(accountId);
			break;
		
		}
		if(account==null){
			throw new BusinessException("This entity didn't exist.");
		}
		return account;
	}
	@Override
	public Account findById(Long id) {
		Account account = accountRepository.findOne(id);
		if(account==null){
			throw new BusinessException("This entity doesn't exist.");
		}
		return account;
	}
	
	@Override
	public<T extends Account> T modify(T account) {
		Account existingAccount = this.checkExistence(account);
		this.checkConsistency(account, existingAccount);
		
		existingAccount.setName(account.getName());
		existingAccount.setAccountStatus(account.getAccountStatus());
		existingAccount.setUserName(account.getUserName());
		existingAccount.setEmail(account.getEmail());
		accountRepository.save(existingAccount);
		return null;
	}
	@Override
	public <T extends Account> void delete(T account) {
		Account existingAccount = this.checkExistence(account);
		switch(existingAccount.getAccountType()){
			default:
				accountRepository.delete(account.getId());
				break;
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
		
		if(existingAccount.getAccountStatus()!=AccountStatus.Pending){
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
	public Account findByUserName(String userName) {
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
		return sessionService.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userName);
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
}
