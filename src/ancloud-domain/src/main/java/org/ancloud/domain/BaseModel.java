package org.ancloud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.ancloud.domain.account.Account;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
//@SQLDelete(sql = "UPDATE #{#entityName} SET state = CURRENT_TIMESTAMP() WHERE id = ?", check = ResultCheckStyle.COUNT)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable{
	
	private static final long serialVersionUID = 8093204186066972966L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("uid")
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private String name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@CreatedBy
	private Account createdBy;
	
	@JsonIgnore
//	@CreatedDate
	@Column(precision=3)
	private DateTime createdDate;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@LastModifiedBy
	private Account lastUpdatedBy;
	
	@JsonIgnore
//	@LastModifiedDate
	@Column(precision=3)
	private DateTime lastUpdatedDate;
	
	@Version
	private Long version = 0L;
	
	@JsonIgnore
	private DateTime deletedDate;

	@PrePersist
	private void prePersist(){
		this.createdDate = new DateTime();
	}
	@PreUpdate
	private void preUpdate(){
		this.lastUpdatedDate = new DateTime();
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
	public DateTime getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(DateTime deletedDate) {
		this.deletedDate = deletedDate;
	}
}
