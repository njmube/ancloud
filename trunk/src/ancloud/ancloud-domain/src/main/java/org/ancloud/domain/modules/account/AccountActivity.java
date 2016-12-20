package org.ancloud.domain.modules.account;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator")
@Table(name = "accountActivity")
public abstract class AccountActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Account operationAccount;

	private Timestamp operationTimeRecord;
	
	@Column(insertable=false, updatable=false)
	private String discriminator;
	
	public AccountActivity(Account account){
		this.operationAccount = account;
		operationTimeRecord = new Timestamp(System.currentTimeMillis());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getOperationAccount() {
		return operationAccount;
	}

	public void setOperationAccount(Account operationAccount) {
		this.operationAccount = operationAccount;
	}

	public Timestamp getOperationTimeRecord() {
		return operationTimeRecord;
	}

	public void setOperationTimeRecord(Timestamp operationTimeRecord) {
		this.operationTimeRecord = operationTimeRecord;
	}

	public String getDiscriminator() {
		return discriminator;
	}
	
}
