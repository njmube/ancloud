package org.ancloud.service.account;

import java.util.List;

import org.ancloud.domain.account.AccountPermission;
import org.ancloud.fw.service.BaseService;
import org.ancloud.repository.BaseModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountPermissionServiceImpl extends BaseService<AccountPermission> implements AccountPermissionService {

	@Override
	public AccountPermission register(AccountPermission entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountPermission> register(List<AccountPermission> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountPermission modify(AccountPermission entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseModelRepository<AccountPermission> getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
