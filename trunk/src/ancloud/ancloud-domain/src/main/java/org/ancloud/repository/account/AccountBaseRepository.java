package org.ancloud.repository.account;

import org.ancloud.domain.account.Account;
import org.ancloud.repository.BaseModelRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AccountBaseRepository<T extends Account> extends BaseModelRepository<T>{

	T findByUserName(String username);
	
	T deleteByUserName(String username);

	int countByIdNotAndUserNameIgnoreCase(Long id, String userName);

	int countByIdNotAndEmail(Long id, String email);

	int countByUserNameIgnoreCase(String userName);

	int countByEmail(String email);
}