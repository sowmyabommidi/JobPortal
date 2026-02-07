package com.jobportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobProfile {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 private int jobId;
	 private int profileid;
	 
	 public int getId() {
		 return id;
	 }
	 public int getJobId() {
		 return jobId;
	 }
	 public int getProfileid() {
		 return profileid;
	 }
	 public void setId(int id) {
		 this.id = id;
	 }
	 public void setJobId(int jobId) {
		 this.jobId = jobId;
	 }
	 public void setProfileid(int profileid) {
		 this.profileid = profileid;
	 }
	 public JobProfile(int id, int jobId, int profileid) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.profileid = profileid;
	 }
	 public JobProfile() {
		super();
	 }
	 @Override
	 public String toString() {
		return "JobProfile [id=" + id + ", jobId=" + jobId + ", profileid=" + profileid + "]";
	 }
	 

}
