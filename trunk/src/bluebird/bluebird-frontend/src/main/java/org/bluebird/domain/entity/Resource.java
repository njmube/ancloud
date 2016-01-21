package org.bluebird.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bb_resource")
public class Resource extends BaseModel{
	
	private static final long serialVersionUID = -4768881665669079490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String key;
	
	private String value;
	
	private String category;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
