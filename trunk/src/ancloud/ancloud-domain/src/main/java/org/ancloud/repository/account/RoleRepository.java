package org.ancloud.repository.account;

import org.ancloud.domain.account.Role;
import org.ancloud.repository.BaseModelRepository;

public interface RoleRepository extends BaseModelRepository<Role>{
	public Role findOneByCodeIgnoreCase(String string);
}

