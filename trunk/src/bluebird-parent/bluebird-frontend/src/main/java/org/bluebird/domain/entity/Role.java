package org.bluebird.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bb_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 4513934956962115145L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String permissionCode) {
		this.roleCode = permissionCode;
	}
}
