package org.ancloud.repository.modules.account;

import org.ancloud.domain.modules.account.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License,Long>{

	License findOneByLicenseKey(String token);
}

