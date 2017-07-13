package org.ancloud.presentation.form;


import org.joda.time.DateTime;

public class LicenseForm extends BaseForm {

	private static final long serialVersionUID = -8050247739504538072L;

	private String licenseKey;
	
	private boolean nonLocked;
	
	private DateTime fromDate;
	
	private DateTime toDate;	

	public DateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}

	public DateTime getToDate() {
		return toDate;
	}

	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public boolean isNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}
}
