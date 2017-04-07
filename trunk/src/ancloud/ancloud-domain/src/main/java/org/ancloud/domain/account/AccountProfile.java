package org.ancloud.domain.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.ancloud.domain.BaseModel;

@Entity
@Table(name = "accountProfile")
@Where(clause = "deletedDate IS NULL")
public class AccountProfile extends BaseModel {

	private static final long serialVersionUID = 3379226982403749296L;

	private String dateFormat = "dd/MM/yyyy";
	
	private String dateTimeFormat = "dd/MM/yyyy HH:mm:ss";

	private String locale = "en_US";
	
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
