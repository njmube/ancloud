package org.ancloud.service.modules.account;

import java.util.Map;

import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AccountSearchCriteria;
import org.ancloud.domain.modules.account.AuthenticationAccountActivity.AuthenticationType;
import org.ancloud.domain.modules.account.enums.AccountStatus;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.session.Session;

public interface AccountService {
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria,Pageable pageable);

	public Account registerNewAccount(Account account);

	public Account findAccountById(Long accountId, Integer accountType);

	public void modifyAccount(Account account);

	public void approveAccount(Account account,AccountStatus status);

	public void registerAuthenticationActivity(Account account, AuthenticationType loginsuccess,String userAgent);

	public Account enableAccount(String userName);
	
	public Account disableAccount(String currentPrincipal);

	public Account findAccountByUserName(String userName);

	Map<String, ? extends Session> getSessionsByUserName(String userName);
}
