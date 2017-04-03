package org.ancloud.domain.blogging;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;

@Entity
@Table(name = "blogging_blog")
public class Blog extends BaseModel {

	private static final long serialVersionUID = 364321079786027902L;

}
