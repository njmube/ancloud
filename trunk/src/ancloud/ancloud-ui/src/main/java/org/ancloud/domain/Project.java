package org.ancloud.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project extends BaseModel {

	private static final long serialVersionUID = 5052310069109902489L;
}
