package org.ancloud.domain.modules.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.ancloud.domain.modules.account.enums.MaritalStatus;
import org.ancloud.domain.modules.account.enums.SexStatus;
import org.joda.time.DateTime;
import org.joda.time.Years;

@MappedSuperclass
public class ProfileAccount extends Account{

	private static final long serialVersionUID = -4424899948512063878L;
	
	@Transient
	private String firstName;
	
	@Transient
	private String lastName;
	
	@Transient
	private Integer age;
	
	private String picturePath;
	
	private String address;
	
	private String workAddress;
	
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus=MaritalStatus.Single;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	private void postLoad(){
		if(this.getBirthday()!=null){
//			Calendar birthdayCalendar = Calendar.getInstance();
//			birthdayCalendar.setTimeInMillis(this.getBirthday().getTime());
//			this.age = Calendar.getInstance().get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);
			
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
}
