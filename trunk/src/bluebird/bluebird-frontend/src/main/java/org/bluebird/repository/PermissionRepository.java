package org.bluebird.repository;

import java.util.List;

import org.bluebird.domain.module.account.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
}
