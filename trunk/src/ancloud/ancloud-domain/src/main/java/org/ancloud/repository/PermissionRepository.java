package org.ancloud.repository;

import java.util.List;

import org.ancloud.domain.account.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
}
