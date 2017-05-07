package org.ancloud.boot.config.properties;

public class Security {
	private int maxSession;
	private String[] permitAlls;
	private String sessionExpiredUrl;
	private String jwtTokenHeader;
	private String jwtTokenSecret;
	private int jwtTokenExpiration;

	public int getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public String getJwtTokenHeader() {
		return jwtTokenHeader;
	}

	public void setJwtTokenHeader(String jwtTokenHeader) {
		this.jwtTokenHeader = jwtTokenHeader;
	}

	public String getJwtTokenSecret() {
		return jwtTokenSecret;
	}

	public void setJwtTokenSecret(String jwtTokenSecret) {
		this.jwtTokenSecret = jwtTokenSecret;
	}

	public int getJwtTokenExpiration() {
		return jwtTokenExpiration;
	}

	public void setJwtTokenExpiration(int jwtTokenExpiration) {
		this.jwtTokenExpiration = jwtTokenExpiration;
	}

	public String getSessionExpiredUrl() {
		return sessionExpiredUrl;
	}

	public void setSessionExpiredUrl(String sessionExpiredUrl) {
		this.sessionExpiredUrl = sessionExpiredUrl;
	}

	public String[] getPermitAlls() {
		return permitAlls;
	}

	public void setPermitAlls(String[] permitAlls) {
		this.permitAlls = permitAlls;
	}

}
