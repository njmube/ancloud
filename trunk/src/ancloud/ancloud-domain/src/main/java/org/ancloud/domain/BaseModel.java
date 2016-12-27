package org.ancloud.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.ancloud.domain.modules.account.Account;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3895185079563549399L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(unique=true)
	protected String code;
	
	protected String name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@CreatedBy
	protected Account createdBy;
	
	@JsonIgnore
//	@CreatedDate
	protected Timestamp createdDate;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@LastModifiedBy
	protected Account lastUpdatedBy;
	
	@JsonIgnore
//	@LastModifiedDate
	protected Timestamp lastUpdatedDate;
	
	@Version
	protected Long version = 0L;

	@PrePersist
	private void prePersist(){
		this.createdDate = new Timestamp(System.currentTimeMillis());
	}
	@PreUpdate
	private void preUpdate(){
		this.lastUpdatedDate = new Timestamp(System.currentTimeMillis());
	}
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
