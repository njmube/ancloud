package org.ancloud.service.account;

import java.util.List;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.service.CrudService;
import org.ancloud.service.authentication.UserDetailsImpl;

public interface AccountService<T extends Account> extends CrudService<T>{

	public T findByIdAndAccountType(Long accountId, AccountType accountType);

	public T findByUserName(String userName);
	
	public void approveAccount(T account,AccountStatus status);

	public void registerAuthenticationActivity(T account, AuthenticationType loginsuccess,String userAgent);

	public T enableAccount(String userName);
	
	public T disableAccount(String currentPrincipal);

	UserDetailsImpl loadUserByUserName(String username);

	UserDetailsImpl loadUserById(Long id);

	List<Account> findAll();

}
