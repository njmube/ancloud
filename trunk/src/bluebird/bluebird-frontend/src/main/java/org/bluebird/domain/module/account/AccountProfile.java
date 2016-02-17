package org.bluebird.domain.module.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bluebird.domain.BaseModel;

@Entity
@Table(name = "bb_accountProfile")
public class AccountProfile extends BaseModel {

	private static final long serialVersionUID = -3559311596801406226L;

	private String dateFormat = "dd/MM/YYYY";

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
}