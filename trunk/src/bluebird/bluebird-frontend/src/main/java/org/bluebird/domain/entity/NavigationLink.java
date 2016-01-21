package org.bluebird.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bb_navigationLink")
public class NavigationLink extends BaseModel{
	
	private static final long serialVersionUID = 2460630851669244383L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String displayMessageCode;
	
	@Column(unique=true)
	private String path;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private NavigationLink parent;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	private Set<NavigationLink> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayMessageCode() {
		return displayMessageCode;
	}

	public void setDisplayMessageCode(String displayMessageCode) {
		this.displayMessageCode = displayMessageCode;
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
	
}
