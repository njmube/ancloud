package org.bluebird.domain.modules.account;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.bluebird.domain.BaseModel;

@Entity
@Table(name = "role")
public class Role extends BaseModel {

	private static final long serialVersionUID = 4513934956962115145L;
	
	@Column(unique=true)
	private String roleCode;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "rolePermission", 
			joinColumns = {
							@JoinColumn(name = "roleCode",
										nullable = false, 
										updatable = false,
										referencedColumnName="roleCode")}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="permissionCode",
												name = "permissionCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Permission> permissions;
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String permissionCode) {
		this.roleCode = permissionCode;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
