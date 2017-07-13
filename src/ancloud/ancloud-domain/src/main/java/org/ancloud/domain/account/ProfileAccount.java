package org.ancloud.domain.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.ancloud.domain.Address;
import org.ancloud.domain.account.enums.MaritalStatus;
import org.ancloud.domain.account.enums.SexStatus;
import org.joda.time.DateTime;
import org.joda.time.Years;

@MappedSuperclass
public class ProfileAccount extends Account {

	private static final long serialVersionUID = -4424899948512063878L;

	private String title;

	private DateTime birthday;
	
	@Transient
	private Integer age;

	private String picturePath;

	private String workAddress;

	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus = MaritalStatus.Single;

	private String occupation;

	private String nationality;

	private String race;
	
	private String callingCode; 
	
	private String contactNumber;
	
	@OneToOne
	private Address address;
	

	@Enumerated(EnumType.ORDINAL)
	private SexStatus sex;

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public Integer getAge() {
		return age;
	}

	@PostLoad
	private void postLoadProfileAccount() {
		if (this.getBirthday() != null) {
			// Calendar birthdayCalendar = Calendar.getInstance();
			// birthdayCalendar.setTimeInMillis(this.getBirthday().getTime());
			// this.age = Calendar.getInstance().get(Calendar.YEAR) -
			// birthdayCalendar.get(Calendar.YEAR);

			DateTime now = new DateTime();
			this.age = Years.yearsBetween(this.getBirthday(), now).getYears();
		}
	}
	

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public SexStatus getSex() {
		return sex;
	}

	public void setSex(SexStatus sex) {
		this.sex = sex;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public DateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(DateTime birthday) {
		this.birthday = birthday;
	}

	public String getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
