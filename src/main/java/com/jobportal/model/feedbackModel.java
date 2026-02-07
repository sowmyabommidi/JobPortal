package com.jobportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class feedbackModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long number;
	private String issue;
	private String feedback;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public long getNumber() {
		return number;
	}
	public String getIssue() {
		return issue;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public feedbackModel(int id, String name, String email, long number, String issue, String feedback) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.issue = issue;
		this.feedback = feedback;
	}
	public feedbackModel() {
		
	}
	@Override
	public String toString() {
		return "feedbackModel [id=" + id + ", name=" + name + ", email=" + email + ", number=" + number + ", issue="
				+ issue + ", feedback=" + feedback + "]";
	}
	 
	
     
}
