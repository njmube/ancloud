package org.ancloud.domain.blogging;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;

@Entity
@Table(name = "blogging_comment")
public class Comment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5178528160223076018L;

}
