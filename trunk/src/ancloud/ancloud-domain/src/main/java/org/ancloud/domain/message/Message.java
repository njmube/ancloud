package org.ancloud.domain.message;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.ancloud.domain.BaseModel;

@Entity
@Table(name = "message",
		uniqueConstraints=@UniqueConstraint(columnNames={"messageKey","language","country"})
)
@Embeddable
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String messageKey;

	private String language;

	private String country;

	private String basename;

	private String variant;

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
