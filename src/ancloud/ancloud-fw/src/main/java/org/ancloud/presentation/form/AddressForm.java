package org.ancloud.presentation.form;


import org.hibernate.validator.constraints.NotEmpty;

public class AddressForm extends BaseForm {

	private static final long serialVersionUID = 8746434495944926915L;
	
	@NotEmpty
	protected String countryCode;
	@NotEmpty
	protected String city;
	@NotEmpty
	protected String state;
	
	@NotEmpty
	protected String addressLine;
	@NotEmpty
	protected String zipCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
