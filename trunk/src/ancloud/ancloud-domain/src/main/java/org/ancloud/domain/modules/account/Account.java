package org.ancloud.domain.modules.account;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ancloud.domain.BaseModel;
import org.ancloud.domain.modules.account.enums.AccountStatus;
import org.ancloud.domain.modules.account.enums.AccountType;
import org.ancloud.fw.core.validation.annotation.Alphanumeric;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
@Inheritance(strategy=InheritanceType.JOINED)
public class Account extends BaseModel {

	private static final long serialVersionUID = 8253111909049644950L;

	@Column(unique=true,length=32)
	@NotEmpty
	@Alphanumeric(message="Field 'userName' is not alphanumeric, can only contains [a-zA-Z0-9_]")
	private String userName;
	
	private String title;
	
	@Column(unique=true)
	private String email;
	
	private String contactNumber;

	@NotEmpty
	private String password;
	
	private DateTime birthday;
	
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
	

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Account approver;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="account")
	private Set<License> licenses;

	public Set<License> getLicenses() {
		return licenses;
	}

	public void setLicenses(Set<License> licenses) {
		this.licenses = licenses;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public DateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(DateTime birthday) {
		this.birthday = birthday;
	}
}
