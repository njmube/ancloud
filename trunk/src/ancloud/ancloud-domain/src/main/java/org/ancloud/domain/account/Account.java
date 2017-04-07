package org.ancloud.domain.account;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.ancloud.domain.BaseModel;
import org.ancloud.domain.account.enums.AccountStatus;
import org.ancloud.domain.account.enums.AccountType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
@Inheritance(strategy=InheritanceType.JOINED)
@Where(clause = "deletedDate IS NULL")
@SQLDelete(sql = "UPDATE account SET deletedDate = CURRENT_DATE WHERE id_product = ? and last_modification_date = ?")
public class Account extends BaseModel {

	private static final long serialVersionUID = -2255091421647057444L;

	private String userName;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@JsonIgnore
	@Transient
	private String reenterPassword;

	@JsonIgnore
	private boolean accountNonExpired;

	@JsonIgnore
	private boolean accountNonLocked;

	@JsonIgnore
	private boolean credentialsNonExpired;

	@Enumerated(EnumType.ORDINAL)
	private AccountStatus accountStatus;
	
	
	@Enumerated(EnumType.ORDINAL)
	private AccountType accountType;
	

	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Account approver;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="account")
	@JsonIgnore
	private Set<AccountLicense> licenses;

	public Set<AccountLicense> getLicenses() {
		return licenses;
	}

	public void setLicenses(Set<AccountLicense> licenses) {
		this.licenses = licenses;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "accountPermission", 
			joinColumns = {
							@JoinColumn(name = "userName",
										referencedColumnName="userName",
										nullable = false, 
										updatable = false)}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="code",
												name = "permissionCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Permission> permissions;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "accountRole", 
			joinColumns = {
							@JoinColumn(name = "userName",
										referencedColumnName="userName",
										nullable = false, 
										updatable = false)}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="code",
												name = "roleCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Role> roles;
	
	@JsonIgnore
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

	public String getReenterPassword() {
		return reenterPassword;
	}

	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
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

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus status) {
		this.accountStatus = status;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Account getApprover() {
		return approver;
	}

	public void setApprover(Account approver) {
		this.approver = approver;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@PostLoad
	public void postLoad() {
		if(StringUtils.isBlank(super.getName())){
			if(StringUtils.isNotBlank(this.firstName)
					|| StringUtils.isNotBlank(this.lastName)) {
				super.setName(this.firstName+" "+this.lastName);
			} else {
				super.setName(this.userName);
			}
		}
	}
	
}
