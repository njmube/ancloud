package org.ancloud.domain.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.ancloud.domain.TimePeriodModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator")
@Table(name = "license")
public class License extends TimePeriodModel{

	private static final long serialVersionUID = 8370160334001511829L;
	
	private boolean nonLocked;

	@Column(insertable=false, updatable=false)
	@JsonIgnore
	private String discriminator;
	
	public boolean isNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}
	
	public String getDiscriminator() {
		return discriminator;
	}
}