package org.ancloud.domain.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("AccountLicense")
@Where(clause = "deletedDate IS NULL")
public class AccountLicense extends License {

	private static final long serialVersionUID = -495181427761841881L;
	
	@ManyToOne
	private Account account;

	@Lob
	private String qrCode;
	
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}