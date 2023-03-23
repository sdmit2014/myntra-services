package com.wecodee.employee.application.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MT_USER")
public class User {

	@Id
	private String userId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String phoneNumber;

	private String countryCode;

	private String password;

	private Integer recordVersion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private List<MtImages> mtImages;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(Integer recordVersion) {
		this.recordVersion = recordVersion;
	}

	public List<MtImages> getMtImages() {
		return mtImages;
	}

	public void setMtImages(List<MtImages> mtImages) {
		this.mtImages = mtImages;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", countryCode=" + countryCode + ", password=" + password
				+ ", recordVersion=" + recordVersion + ", mtImages=" + mtImages + "]";
	}

}
