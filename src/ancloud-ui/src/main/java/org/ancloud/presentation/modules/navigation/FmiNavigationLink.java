package org.ancloud.presentation.modules.navigation;

import org.hibernate.validator.constraints.NotEmpty;


public class FmiNavigationLink {
	
	private Long id;
	
	@NotEmpty
	private String messageKey;
	
	private String message;

	private String icon;

	private String path;
	
	private String groupId;
	
	private String groupIndex;
	
	private String itemIndex;

	public String getMessageKey() {
		return messageKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
