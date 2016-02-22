package org.bluebird.service.module.account;

import javax.inject.Inject;

import org.bluebird.domain.Project;
import org.bluebird.domain.module.account.Account;
import org.bluebird.domain.module.account.AccountSearchCriteria;
import org.bluebird.repository.AccountRepository;
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
