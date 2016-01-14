package org.bluebird.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bb_resource")
public class Resource {
	
	@Id
	private String key;
	
	private String value;
	
	private String category;
	
}
