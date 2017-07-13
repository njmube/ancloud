package org.ancloud.service.account;

public interface LoginAttemptService {

	public void loginSucceeded(String key,String sesionId);

	public void loginFailed(String key,String sesionId);

	public boolean isBlocked(String key);
}