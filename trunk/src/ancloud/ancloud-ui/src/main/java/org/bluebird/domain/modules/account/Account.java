package org.bluebird.domain.modules.account;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bluebird.domain.BaseModel;

@Entity
@Table(name = "account")
public class Account extends BaseModel {

	private static final long serialVersionUID = -3559311596801406226L;

	@Column(unique=true)
	private String userName;
	
	private String title;

	private String password;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "accountPermission", 
			joinColumns = {
							@JoinColumn(name = "userName",
										referencedColumnName="userName",
										nullable = false, 
										updatable = false)}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="permissionCode",
												name = "permissionCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Permission> permissions;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "accountrole", 
			joinColumns = {
							@JoinColumn(name = "userName",
										referencedColumnName="userName",
										nullable = false, 
										updatable = false)}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="roleCode",
												name = "roleCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Role> roles;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	private List<AccountProfile> accountProfiles;

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<AccountProfile>getAccountProfiles() {
		return accountProfiles;
	}

	public void setAccountProfiles(List<AccountProfile> accountProfiles) {
		this.accountProfiles = accountProfiles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
