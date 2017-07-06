package org.ancloud.repository.jpa;

import org.ancloud.domain.account.Role;
import org.ancloud.repository.BaseModelRepository;

public interface RoleRepository extends BaseModelRepository<Role>{
	public Role findOneByCodeIgnoreCase(String string);
}

