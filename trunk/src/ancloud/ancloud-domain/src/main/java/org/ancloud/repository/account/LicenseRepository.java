package org.ancloud.repository.account;

import org.ancloud.domain.account.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License,Long>{

	License findOneByLicenseKey(String token);
}

