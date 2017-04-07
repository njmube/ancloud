package org.ancloud.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deletedDate IS NULL")
public class Address extends BaseModel {
	
	private static final long serialVersionUID = -4204699449282030705L;
	
	private String country;
	private String city;
	private String state;
	private String addressLine;
	private String zipCode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}