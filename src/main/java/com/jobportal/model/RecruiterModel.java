package com.jobportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecruiterModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String gender;
	private String email;
	private String contactNumber;
	private String organization;
	private String organizationRole;
	private String password;
	public int getId() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getOrganization() {
		return organization;
	}
	public String getOrganizationRole() {
		return organizationRole;
	}
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void setOrganizationRole(String organizationRole) {
		this.organizationRole = organizationRole;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RecruiterModel(int id, String fullName, String gender, String email, String contactNumber,
			String organization, String organizationRole, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.gender = gender;
		this.email = email;
		this.contactNumber = contactNumber;
		this.organization = organization;
		this.organizationRole = organizationRole;
		this.password = password;
	}
	public RecruiterModel() {
		
	}
	@Override
	public String toString() {
		return "RecruiterModel [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", organization=" + organization + ", organizationRole="
				+ organizationRole + ", password=" + password + "]";
	}

}
