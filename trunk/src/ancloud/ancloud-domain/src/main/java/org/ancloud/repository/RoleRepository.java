package org.ancloud.repository;

import org.ancloud.domain.account.Role;

public interface RoleRepository extends BaseRepository<Role>{

	public Role findOneByCodeIgnoreCase(String string);
}

