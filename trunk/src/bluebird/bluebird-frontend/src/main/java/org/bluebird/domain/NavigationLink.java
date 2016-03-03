package org.bluebird.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "bb_navigationLink")
public class NavigationLink extends BaseModel{
	
	private static final long serialVersionUID = 2460630851669244383L;

	private String messageKey;
	
	private String message;
	
	private String icon;
	
	private String path;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinColumns({
		@JoinColumn(referencedColumnName = "code")
	})
	private NavigationLink parent;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent",cascade=CascadeType.REMOVE)
	private Set<NavigationLink> children = new HashSet<NavigationLink>();

	private String groupId;
	
	private String groupIndex;
	
	private Integer itemIndex;
	
	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
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

	public NavigationLink getParent() {
		return parent;
	}

	public void setParent(NavigationLink parent) {
		this.parent = parent;
	}

	public Set<NavigationLink> getChildren() {
		return children;
	}

	public void setChildren(Set<NavigationLink> children) {
		this.children = children;
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

	public Integer getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
