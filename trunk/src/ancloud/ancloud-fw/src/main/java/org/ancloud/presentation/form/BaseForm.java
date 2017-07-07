package org.ancloud.presentation.form;


import java.io.Serializable;

import org.ancloud.domain.account.Account;
import org.ancloud.fw.presentation.validation.annotation.Alphanumeric;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;


public abstract class BaseForm implements Serializable{
	
	private static final long serialVersionUID = -5598697622306007761L;

	private Long id;
	
	@Alphanumeric(message="Field 'userName' is not alphanumeric, can only contains [a-zA-Z0-9_]")
	protected String code;
	
	@Length(max=254)
	private String name;
	
	private Account createdBy;
	
	private DateTime createdDate;
	
	private Account lastUpdatedBy;
	
	private DateTime lastUpdatedDate;
	
	private Long version = 0L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Account getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Account createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createDate) {
		this.createdDate = createDate;
	}

	public Account getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Account updatedBy) {
		this.lastUpdatedBy = updatedBy;
	}

	public DateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(DateTime updatedDate) {
		this.lastUpdatedDate = updatedDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
