package org.ancloud.service.account;

import javax.transaction.Transactional;

import org.ancloud.domain.account.Account;
import org.ancloud.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AccountServiceImpl extends BaseAccountService<Account> implements AccountService<Account> {
	@Override
	public BaseRepository<Account> getRepository() {
		return this.accountRepository;
	}
}
