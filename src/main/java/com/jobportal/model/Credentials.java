package com.jobportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credentials {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String role;
	private String email;
	private String password;
	public Credentials(int id,String role, String email, String password) {
	   this.id=id;
		this.role = role;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Credentials() {
		super();
	}
	@Override
	public String toString() {
		return "Credentials [id=" + id + ", role=" + role + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
