package org.bluebird.domain.entity;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseModel implements Serializable{
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
