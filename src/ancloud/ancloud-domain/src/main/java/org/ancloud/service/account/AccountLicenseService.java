package org.ancloud.service.account;

import org.ancloud.domain.account.AccountLicense;
import org.ancloud.service.CrudService;

public interface AccountLicenseService extends CrudService<AccountLicense> {
	public boolean lockLicense(String key);

	public AccountLicense findByAuthenticationToken(String authenticationToken);

	public String getQrCodeFile(AccountLicense map);

}
