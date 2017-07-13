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

	private static final long serialVersionUID = 5830125153427440216L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userName",referencedColumnName="userName")
	private Account account;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="permissionCode",referencedColumnName="code")
	private Permission permission;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
}
