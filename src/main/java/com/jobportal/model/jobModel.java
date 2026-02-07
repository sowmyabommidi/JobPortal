package com.jobportal.model;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Jobs")
public class jobModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String Company;
	private String Code;
	private String Title;
	private String Description;
	private String Requirements;
	private String EmployementType;
	private String ExperienceLevel;
	private String Package;
	private String workType;
	private String Location;
	private String url;
	
	public int getId() {
		return id;
	}
	public String getCompany() {
		return Company;
	}
	
	
	public String getCode() {
		return Code;
	}
	public String getTitle() {
		return Title;
	}
	public String getDescription() {
		return Description;
	}
	public String getRequirements() {
		return Requirements;
	}
	public String getEmployementType() {
		return EmployementType;
	}
	public String getExperienceLevel() {
		return ExperienceLevel;
	}
	public String getPackage() {
		return Package;
	}
	public String getWorkType() {
		return workType;
	}
	public String getUrl() {
		return url;
	}
	public String getLocation() {
		return Location;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public void setCode(String code) {
		Code = code;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setRequirements(String requirements) {
		Requirements = requirements;
	}
	public void setEmployementType(String employementType) {
		EmployementType = employementType;
	}
	public void setExperienceLevel(String experienceLevel) {
		ExperienceLevel = experienceLevel;
	}
	public void setPackage(String package1) {
		Package = package1;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public jobModel(int id, String company, String code, String title, String description, String requirements,
			String employementType, String experienceLevel, String package1, String workType, String url,String location) {
		
		this.id = id;
		Company = company;
		Code = code;
		Title = title;
		Description = description;
		Requirements = requirements;
		EmployementType = employementType;
		ExperienceLevel = experienceLevel;
		Package = package1;
		this.workType = workType;
		this.url = url;
		Location=location;
		
	}
	public jobModel() {
		
	}
	@Override
	public String toString() {
		return "jobModel [id=" + id + ", Company=" + Company + ", Code=" + Code + ", Title=" + Title + ", Description="
				+ Description + ", Requirements=" + Requirements + ", EmployementType=" + EmployementType
				+ ", ExperienceLevel=" + ExperienceLevel + ", Package=" + Package + ", workType=" + workType + ", url="
				+ url + ",Location"+Location+"]";
	}
	

}
