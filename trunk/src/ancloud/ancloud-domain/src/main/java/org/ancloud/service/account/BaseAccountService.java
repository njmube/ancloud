package org.ancloud.service.account;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.Role;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;
import org.ancloud.fw.core.exception.BusinessException;
import org.ancloud.repository.AccountRepository;
import org.ancloud.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class BaseAccountService {

	@Inject 
	protected AccountRepository accountRepository;

	@Inject
	protected RoleRepository roleRepository;
	
	
	
	@Inject
	protected PasswordEncoder passwordEncoder;
	
	protected void setCommonBeforeRegister(Account account) {
		this.setCommonBeforeRegister(account, account.getAccountType());
	}
	
	protected void setCommonBeforeRegister(Account account, AccountType accountType) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.setAccountNonExpired(true);
		account.setAccountNonLocked(true);
		account.setCredentialsNonExpired(true);
		account.setAccountStatus(AccountStatus.Enabled);
		String roleCode = null;
		account.setAccountType(accountType);
		switch (accountType) {
		default:
			roleCode = "Administrator";
			break;
		}
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepository.findOneByCodeIgnoreCase(roleCode);
		if (role != null) {
			roles.add(role);
			account.setRoles(roles);
		}
	}

	protected void setCommonBeforeModify(Account account, Account existedAccount) {
		existedAccount.setName(account.getName());
		existedAccount.setAccountStatus(account.getAccountStatus());
	}
	
	@SuppressWarnings("unchecked")
	protected<T extends Account> T checkExistence(T account) {
		T existingAccount = (T) accountRepository.findOne(account.getId());
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
