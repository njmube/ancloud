package org.bluebird.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.bluebird.domain.module.account.Account;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Account createdBy;
	
	private Timestamp createDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Account updatedBy;
	
	private Timestamp updatedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;

	public Account getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Account createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Account getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Account updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
