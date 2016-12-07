package org.ancloud.service.modules.account;

import javax.inject.Inject;

import org.ancloud.domain.Project;
import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AccountSearchCriteria;
import org.ancloud.repository.modules.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Inject 
	AccountRepository accountRepository;
	
	@Override
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria,Pageable pageable) {
		return accountRepository.findAll(criteria,pageable);
	}
}
