package org.ancloud.domain.modules.message;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.ancloud.domain.ProjectBaseModel;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "message",
		uniqueConstraints=@UniqueConstraint(columnNames={"messageKey","language","country"})
)
@Embeddable
@Indexed()
public class Message extends ProjectBaseModel {

	private static final long serialVersionUID = -8644123651815590065L;
	
	@Field
	private String messageKey;

	@Field
	private String language;

	@Field
	private String country;

	@Field
	private String basename;

	private String variant;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String message;

	public Message() {
	}

	public String getKey() {
		return messageKey;
	}

	public void setKey(String key) {
		this.messageKey = key;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
