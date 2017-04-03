package org.ancloud.service.account;

import org.ancloud.domain.AuthenticationToken;
import org.ancloud.domain.account.License;

public interface LicenseService {
	public boolean lockLicense(String key);
	public License createNewLicense(License license);
	public Object getAllSessions();
	public License getLicense(String authenticationToken);
}
