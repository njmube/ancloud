package org.ancloud.presentation.form;

import org.ancloud.domain.enums.MaritalStatus;
import org.ancloud.domain.enums.SexStatus;

public class ProfileAccountForm extends AccountForm{

	private static final long serialVersionUID = -4319385360518395111L;

	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private String picturePath;
	
	private String address;
	
	private String workAddress;
	
	private MaritalStatus maritalStatus=MaritalStatus.Single;
	
	private String occupation;
	
	private String nationality;
	
	private String race;
	
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
