package org.bluebird.presentation.module.navigation;

import org.hibernate.validator.constraints.NotEmpty;


public class FmiNavigationLink {
	
	private Long id;
	
	@NotEmpty
	private String messageCode;

	private String icon;

	private String path;
	
	private String groupId;
	
	private String groupIndex;
	
	private String itemIndex;

	public String getMessageCode() {
		return messageCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(String groupIndex) {
		this.groupIndex = groupIndex;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}
}
