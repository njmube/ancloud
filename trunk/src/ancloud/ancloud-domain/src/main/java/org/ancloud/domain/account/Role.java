package org.ancloud.domain.account;

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

import org.ancloud.domain.ProjectBaseModel;

@Entity
@Table(name = "role")
public class Role extends ProjectBaseModel {

	private static final long serialVersionUID = 4513934956962115145L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "rolePermission", 
			joinColumns = {
							@JoinColumn(name = "roleCode",
										nullable = false, 
										updatable = false,
										referencedColumnName="code")}, 
			inverseJoinColumns = {
									@JoinColumn(referencedColumnName="code",
												name = "permissionCode", 
												nullable = false, 
												updatable = false)
								})
	private Set<Permission> permissions;
	
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
