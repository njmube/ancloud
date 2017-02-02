package org.ancloud.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticationToken{
	
	@NotEmpty
	private String token;

	public AuthenticationToken(String token){
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}