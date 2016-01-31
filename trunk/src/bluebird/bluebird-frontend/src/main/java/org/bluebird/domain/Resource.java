package org.bluebird.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bb_resource")
public class Resource implements Serializable{
	
	private static final long serialVersionUID = -4768881665669079490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String key;
	
	private String messageKey;
	
	private String value;
	
	private String category;
	
	private String resourceGroup;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
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

	public String getResourceGroup() {
		return resourceGroup;
	}

	public void setResourceGroup(String resourceGroup) {
		this.resourceGroup = resourceGroup;
	}
	
}
