package org.ancloud.domain.blogging;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;

@Entity
@Table(name = "blogging_post")
public class Post extends BaseModel {

	private static final long serialVersionUID = 7454726726218134747L;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
