package org.ancloud.domain.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.ancloud.domain.Address;
import org.ancloud.domain.enums.MaritalStatus;
import org.ancloud.domain.enums.SexStatus;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

@MappedSuperclass
public class ProfileAccount extends Account {

	private String title;

	private String contactNumber;

	private DateTime birthday;

	private String firstName;
	
	private String lastName;

	@Transient
	private Integer age;

	private String picturePath;

	private Address address;

	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus = MaritalStatus.Single;

	private String occupation;

	private String nationality;

	private String race;

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

	public Integer getAge() {
		return age;
	}

	@PostLoad
	private void postLoadSetAge() {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@PostLoad
	public void postLoad() {
		if(StringUtils.isBlank(super.getName())){
			if(StringUtils.isNotBlank(this.firstName)
					|| StringUtils.isNotBlank(this.lastName)) {
				super.setName(this.firstName+" "+this.lastName);
			} else {
				super.setName(this.userName);
			}
		}
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
