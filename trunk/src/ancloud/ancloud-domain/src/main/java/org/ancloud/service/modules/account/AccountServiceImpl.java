package org.ancloud.service.modules.account;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.modules.account.Account;
import org.ancloud.domain.modules.account.AccountSearchCriteria;
import org.ancloud.domain.modules.account.Role;
import org.ancloud.domain.modules.account.enums.AccountStatus;
import org.ancloud.domain.modules.account.enums.AccountType;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.repository.modules.RoleRepository;
import org.ancloud.repository.modules.account.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Inject
	AccountRepository accountRepository;

	@Inject
	RoleRepository roleRepository;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Override
	public Page<Account> findAllAccountByCriteria(AccountSearchCriteria criteria, Pageable pageable) {
		return accountRepository.findAll(criteria, pageable);
	}

	@Override
	public Account registerNewAccount(Account account) {
		this.validateBeforeRegister(account);
		this.setCommonBeforeRegister(account, AccountType.Admin);

		accountRepository.save(account);
		return account;
	}

	private void setCommonBeforeModify(Account account, Account existedAccount) {
		existedAccount.setUserName(account.getUserName());
		existedAccount.setPassword(account.getPassword());
		existedAccount.setName(account.getName());
		existedAccount.setAccountStatus(account.getAccountStatus());
	}

	private void setCommonBeforeRegister(Account account, AccountType accountType) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setAccountNonExpired(true);
		account.setAccountNonLocked(true);
		account.setCredentialsNonExpired(true);
		account.setAccountStatus(AccountStatus.Enabled);
		String roleCode = null;
		switch (accountType) {
		case Admin:
			account.setAccountType(0);
			roleCode = "admin";
			break;
		}
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findOneByCodeIgnoreCase(roleCode);
		if (role != null) {
			roles.add(role);
			account.setRoles(roles);
		}
	}

	private void validateBeforeModify(Account account, Account existedAccount) {
		if (existedAccount == null) {
			throw new BusinessException("This user didn't exist.");
		}
		if (accountRepository.countByIdNotAndUserNameIgnoreCase(account.getId(), account.getUserName()) > 0) {
			throw new BusinessException("User with this user name already exists.");
		}
		if (accountRepository.countByIdNotAndEmail(account.getId(), account.getEmail()) > 0) {
			throw new BusinessException("User with this email already exists.");
		}
		if (account.getVersion() != existedAccount.getVersion()) {
			throw new BusinessException("User's data was old. Try to fetch new data before modify.");
		}
	}

	private void validateBeforeRegister(Account account) {
		if (accountRepository.countByUserNameIgnoreCase(account.getUserName()) > 0) {
			throw new BusinessException("User with this user name already exists.");
		}
		if (StringUtils.isNotEmpty(account.getEmail()))
			if (accountRepository.countByEmail(account.getEmail()) > 0) {
				throw new BusinessException("User with this email already exists.");
			}
	}
}
