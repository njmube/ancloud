package org.bluebird.domain.modules.account;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.bluebird.domain.BaseModel;

@Entity
@Table(name = "permission")
public class Permission extends BaseModel{
	
	private static final long serialVersionUID = 4396393555601831943L;

	@Column(unique=true)
	private String permissionCode;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="permissions")
	private Set<Account> accounts;

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
}
