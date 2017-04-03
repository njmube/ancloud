package org.ancloud.service.account;

public interface LoginAttemptService {

	public void loginSucceeded(String key);

	public void loginFailed(String key);

	public boolean isBlocked(String key);
}