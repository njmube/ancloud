package org.ancloud.domain.modules.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("AuthenticationAccountActivity")
public class AuthenticationAccountActivity extends AccountActivity{
	
	@Enumerated(EnumType.ORDINAL)
	private AuthenticationType authenticationType;
	
	public AuthenticationAccountActivity(Account account,AuthenticationType authenticationType){
		super(account);
		this.authenticationType = authenticationType;
	}
	
	
	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}

	public enum AuthenticationType{
		LogInSuccess,LogInFailed,LogOut,SessionTimeOut
	}
}
