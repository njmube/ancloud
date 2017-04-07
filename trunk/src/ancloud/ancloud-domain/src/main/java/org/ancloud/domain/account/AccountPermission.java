package org.ancloud.domain.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;

@Entity
@Table(name="accountPermission")
public class AccountPermission extends BaseModel{

	private static final long serialVersionUID = 4415413852577210103L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userName",columnDefinition="userName")
	private Account account;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="permissionCode",columnDefinition="permissionCode")
	private Permission permission;
	
}
