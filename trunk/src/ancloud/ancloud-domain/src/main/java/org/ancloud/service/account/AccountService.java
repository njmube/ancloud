package org.ancloud.service.account;

import java.util.Map;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.service.authentication.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.session.Session;

public interface AccountService {

	public Page<Account> findAllByCriteria(Specification<Account> criteria, Pageable pageable);
	
	public<T extends Account> T registerNew(T account);
	
	public<T extends Account> T modify(T account);
	
	public<T extends Account> void delete(T account);
	
	public Account findById(Long id);
	
	public Account findByIdAndAccountType(Long accountId, AccountType accountType);

	public Account findByUserName(String userName);
	
	public void approveAccount(Account account,AccountStatus status);

	public void registerAuthenticationActivity(Account account, AuthenticationType loginsuccess,String userAgent);

	public Account enableAccount(String userName);
	
	public Account disableAccount(String currentPrincipal);

	public Map<String, ? extends Session> getSessionsByUserName(String userName);

	UserDetailsImpl loadUserByUserName(String username);

	UserDetailsImpl loadUserById(Long id);

}
