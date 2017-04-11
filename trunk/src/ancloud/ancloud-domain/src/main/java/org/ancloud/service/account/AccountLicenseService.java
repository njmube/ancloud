package org.ancloud.service.account;

import org.ancloud.domain.account.AccountLicense;
import org.ancloud.fw.service.CrudService;
import org.springframework.core.io.Resource;

public interface AccountLicenseService extends CrudService<AccountLicense> {
	public boolean lockLicense(String key);

	public Object getAllSessions();

	public AccountLicense findByAuthenticationToken(String authenticationToken);

	public String getQrCodeFile(AccountLicense map);

}
