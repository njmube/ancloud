package org.ancloud.domain.modules.account;

import javax.persistence.Embeddable;

@Embeddable
public interface ActivityType {
	
	String getName();
	
	String getFullClass();
	
	public enum AuthenticationActivityType implements ActivityType{
		Login,
		Logout,
		SessionTimeout;

		@Override
		public String getName() {
			return this.name();
		}

		@Override
		public String getFullClass() {
			return this.getClass().getCanonicalName();
		}
	}
}
