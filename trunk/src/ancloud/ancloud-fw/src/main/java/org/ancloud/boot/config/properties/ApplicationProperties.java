package org.ancloud.boot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private Database database;

	private ConnectionPool cp;

	private Jpa jpa;

	private Security security;

	public ApplicationProperties() {

	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
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

	

	

	

	
}
