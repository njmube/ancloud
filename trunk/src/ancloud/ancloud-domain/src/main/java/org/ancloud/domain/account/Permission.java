package org.ancloud.domain.account;

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

import org.ancloud.domain.ProjectBaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "permission")
public class Permission extends ProjectBaseModel{
	
	private static final long serialVersionUID = 4396393555601831943L;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="permissions")
	private Set<Account> accounts;

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
