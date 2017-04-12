package org.ancloud.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private DataBase database;

	private ConnectionPool cp;

	private Jpa jpa;
	
	private Security security;

	public DataBase getDatabase() {
		return database;
	}

	public void setDatabase(DataBase database) {
		this.database = database;
	}

	public ConnectionPool getCp() {
		return cp;
	}

	public void setCp(ConnectionPool cp) {
		this.cp = cp;
	}

	public Jpa getJpa() {
		return jpa;
	}

	public void setJpa(Jpa jpa) {
		this.jpa = jpa;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public class ConnectionPool {
		private int maxActive;
		private int maxIdle;
		private int minIdle;
		private int maxWaitMillis;

		public int getMaxActive() {
			return maxActive;
		}

		public void setMaxActive(int maxActive) {
			this.maxActive = maxActive;
		}

		public int getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public int getMinIdle() {
			return minIdle;
		}

		public void setMinIdle(int minIdle) {
			this.minIdle = minIdle;
		}

		public int getMaxWaitMillis() {
			return maxWaitMillis;
		}

		public void setMaxWaitMillis(int maxWaitMillis) {
			this.maxWaitMillis = maxWaitMillis;
		}
	}

	public class DataBase {
		private String url;
		private String userName;
		private String password;
		private String driverClassName;
		private String dialect;
		private Boolean showSql=false;

		public Boolean getShowSql() {
			return showSql;
		}

		public void setShowSql(Boolean showSql) {
			this.showSql = showSql;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDriverClassName() {
			return driverClassName;
		}

		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}

		public String getDialect() {
			return dialect;
		}

		public void setDialect(String dialect) {
			this.dialect = dialect;
		}
	}

	public class Jpa {
		private String schemaGeneration;
		private String schemaScriptGeneration;
		private String schemaScriptGenerationCreateTarget;
		private String schemaScriptGenerationDropTarget;

		public String getSchemaGeneration() {
			return schemaGeneration;
		}

		public void setSchemaGeneration(String schemaGeneration) {
			this.schemaGeneration = schemaGeneration;
		}

		public String getSchemaScriptGeneration() {
			return schemaScriptGeneration;
		}

		public void setSchemaScriptGeneration(String schemaScriptGeneration) {
			this.schemaScriptGeneration = schemaScriptGeneration;
		}

		public String getSchemaScriptGenerationCreateTarget() {
			return schemaScriptGenerationCreateTarget;
		}

		public void setSchemaScriptGenerationCreateTarget(String schemaScriptGenerationCreateTarget) {
			this.schemaScriptGenerationCreateTarget = schemaScriptGenerationCreateTarget;
		}

		public String getSchemaScriptGenerationDropTarget() {
			return schemaScriptGenerationDropTarget;
		}

		public void setSchemaScriptGenerationDropTarget(String schemaScriptGenerationDropTarget) {
			this.schemaScriptGenerationDropTarget = schemaScriptGenerationDropTarget;
		}
	}

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

}
