package org.bluebird.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.bluebird.domain.modules.account.Account;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@CreatedBy
	private Account createdBy;
	
	@CreatedDate
	private Timestamp createdDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@LastModifiedBy
	private Account lastUpdatedBy;
	
	@LastModifiedDate
	private Timestamp lastUpdatedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;
	
	@Version
	private Long version;

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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createDate) {
		this.createdDate = createDate;
	}

	public Account getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Account updatedBy) {
		this.lastUpdatedBy = updatedBy;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp updatedDate) {
		this.lastUpdatedDate = updatedDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
