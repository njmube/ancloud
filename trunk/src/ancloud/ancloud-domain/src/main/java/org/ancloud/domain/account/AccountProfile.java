package org.ancloud.domain.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.ancloud.domain.ProjectBaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accountProfile")
public class AccountProfile extends ProjectBaseModel {

	private static final long serialVersionUID = 2388675280757662749L;

	private String dateFormat = "dd/MM/YYYY";
	
	private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	private String locale = "en_US";
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Account parent;
	
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Account getParent() {
		return parent;
	}

	public void setParent(Account parent) {
		this.parent = parent;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public String getLocale() {
		return this.locale;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}
}