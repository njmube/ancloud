package org.ancloud.domain.account;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "permission")
@Where(clause = "deletedDate IS NULL")
public class Permission extends BaseModel{
	
	private static final long serialVersionUID = 4396393555601831943L;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="permissions")
	private Set<Account> accounts;

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
