package org.ancloud.domain.account;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ancloud.domain.TimePeriodModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "license")
public class License extends TimePeriodModel{

	private static final long serialVersionUID = 8370160334001511829L;
	
	private String licenseKey;
	
	private boolean nonLocked;

	@ManyToOne
	@JsonIgnore
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String key) {
		this.licenseKey = key;
	}

	public boolean isNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}
}