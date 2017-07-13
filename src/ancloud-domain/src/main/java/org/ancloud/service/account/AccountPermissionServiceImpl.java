package org.ancloud.service.account;

import java.util.List;

import org.ancloud.domain.account.AccountPermission;
import org.ancloud.repository.BaseRepository;
import org.ancloud.service.BaseService;
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
	public AccountPermission modify(AccountPermission entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
