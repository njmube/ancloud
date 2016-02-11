package org.bluebird.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bb_navigationLink")
public class NavigationLink extends BaseModel{
	
	private static final long serialVersionUID = 2460630851669244383L;

	private String messageCode;
	
	private String icon;
	
	@Column(unique=true)
	private String path;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="path")
	private NavigationLink parent;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	private Set<NavigationLink> children;

	private String groupId;
	
	private String groupIndex;
	
	public String getMessageCode() {
		return messageCode;
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
	
}
