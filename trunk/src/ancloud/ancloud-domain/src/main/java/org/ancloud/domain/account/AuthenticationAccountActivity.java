package org.ancloud.domain.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("AuthenticationAccountActivity")
public class AuthenticationAccountActivity extends AccountActivity{
	
	@Enumerated(EnumType.ORDINAL)
	private AuthenticationType authenticationType;

	private String userAgent;
	
	public AuthenticationAccountActivity(Account account,AuthenticationType authenticationType,String userAgent){
		super(account);
		this.authenticationType = authenticationType;
		this.userAgent = userAgent;
	}
	
	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public enum AuthenticationType{
		LogInSuccess,
		LogInFailed,
		LogOut,
		SessionTimeOut
	}
}