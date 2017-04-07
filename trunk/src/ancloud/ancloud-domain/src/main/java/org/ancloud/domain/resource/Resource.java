package org.ancloud.domain.resource;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "resource",
	uniqueConstraints=@UniqueConstraint(columnNames={"category", "resourceKey"}))

public class Resource implements Serializable{
	
	private static final long serialVersionUID = -4768881665669079490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("uid")
	private Long id;
	
	private String resourceKey;
	
	private String messageKey;
	
	private String value;
	
	private String value2;
	
	private String value3;
	
	private String category;
	
	private String resourceGroup;

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String key) {
		this.resourceKey = key;
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

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
}
