package com.jobportal.model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

public class applicant {
	
	private MultipartFile resume;
	
	
	public MultipartFile getResume() {
		return resume;
	}
	
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
	public applicant(MultipartFile resume) {
	
		this.resume = resume;
	}
	public applicant() {
		
	}
	@Override
	public String toString() {
		return "applicant [ resume=" + resume + "]";
	}
	
	
	
}
