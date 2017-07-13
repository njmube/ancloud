package org.ancloud.domain;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ProjectBaseModel extends BaseModel{
	
	private static final long serialVersionUID = 795416684330467180L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
