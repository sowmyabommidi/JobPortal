package com.jobportal.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class ApplicantModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fullName;
	private String email;
	private String dateOfBirth;
	private long contactNumber;
	private String city;
	private String gender;
  private String profileSummary;
  private String language;
  private String Internship;
  private String Achievements;
  private String Projects;
    private String password;
	private String skills;
	private String fileName;
	private String SName;
	private String SBoard;
	private String SScore;
	private String SPassingYear;
	private String HName;
	private String HBoard;
	private String HScore;
	private String HPassingYear;
	private String GName;
	private String GBoard;
	private String GScore;
	private String GPassingYear;
	private String Experience;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] resume;
	public int getId() {
		return id;
	}
	public String getFullName() {
		return fullName;
	}
	public String getEmail() {
		return email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public String getCity() {
		return city;
	}
	public String getGender() {
		return gender;
	}
	public String getProfileSummary() {
		return profileSummary;
	}
	public String getLanguage() {
		return language;
	}
	public String getInternship() {
		return Internship;
	}
	public String getAchievements() {
		return Achievements;
	}
	public String getProjects() {
		return Projects;
	}
	public String getPassword() {
		return password;
	}
	public String getSkills() {
		return skills;
	}
	public String getFileName() {
		return fileName;
	}
	public String getSName() {
		return SName;
	}
	public String getSBoard() {
		return SBoard;
	}
	public String getSScore() {
		return SScore;
	}
	public String getSPassingYear() {
		return SPassingYear;
	}
	public String getHName() {
		return HName;
	}
	public String getHBoard() {
		return HBoard;
	}
	public String getHScore() {
		return HScore;
	}
	public String getHPassingYear() {
		return HPassingYear;
	}
	public String getGName() {
		return GName;
	}
	public String getGBoard() {
		return GBoard;
	}
	public String getGScore() {
		return GScore;
	}
	public String getGPassingYear() {
		return GPassingYear;
	}
	public String getExperience() {
		return Experience;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setInternship(String internship) {
		Internship = internship;
	}
	public void setAchievements(String achievements) {
		Achievements = achievements;
	}
	public void setProjects(String projects) {
		Projects = projects;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public void setSBoard(String sBoard) {
		SBoard = sBoard;
	}
	public void setSScore(String sScore) {
		SScore = sScore;
	}
	public void setSPassingYear(String sPassingYear) {
		SPassingYear = sPassingYear;
	}
	public void setHName(String hName) {
		HName = hName;
	}
	public void setHBoard(String hBoard) {
		HBoard = hBoard;
	}
	public void setHScore(String hScore) {
		HScore = hScore;
	}
	public void setHPassingYear(String hPassingYear) {
		HPassingYear = hPassingYear;
	}
	public void setGName(String gName) {
		GName = gName;
	}
	public void setGBoard(String gBoard) {
		GBoard = gBoard;
	}
	public void setGScore(String gScore) {
		GScore = gScore;
	}
	public void setGPassingYear(String gPassingYear) {
		GPassingYear = gPassingYear;
	}
	public void setExperience(String experience) {
		Experience = experience;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public ApplicantModel(int id, String fullName, String email, String dateOfBirth, long contactNumber, String city,
			String gender, String profileSummary, String language, String internship, String achievements,
			String projects, String password, String skills, String fileName, String sName, String sBoard,
			String sScore, String sPassingYear, String hName, String hBoard, String hScore, String hPassingYear,
			String gName, String gBoard, String gScore, String gPassingYear, String experience, byte[] resume) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.city = city;
		this.gender = gender;
		this.profileSummary = profileSummary;
		this.language = language;
		Internship = internship;
		Achievements = achievements;
		Projects = projects;
		this.password = password;
		this.skills = skills;
		this.fileName = fileName;
		SName = sName;
		SBoard = sBoard;
		SScore = sScore;
		SPassingYear = sPassingYear;
		HName = hName;
		HBoard = hBoard;
		HScore = hScore;
		HPassingYear = hPassingYear;
		GName = gName;
		GBoard = gBoard;
		GScore = gScore;
		GPassingYear = gPassingYear;
		Experience = experience;
		this.resume = resume;
	}
	public ApplicantModel() {
		super();
	}
	@Override
	public String toString() {
		return "ApplicantModel [id=" + id + ", fullName=" + fullName + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", contactNumber=" + contactNumber + ", city=" + city + ", gender=" + gender
				+ ", profileSummary=" + profileSummary + ", language=" + language + ", Internship=" + Internship
				+ ", Achievements=" + Achievements + ", Projects=" + Projects + ", password=" + password + ", skills="
				+ skills + ", fileName=" + fileName + ", SName=" + SName + ", SBoard=" + SBoard + ", SScore=" + SScore
				+ ", SPassingYear=" + SPassingYear + ", HName=" + HName + ", HBoard=" + HBoard + ", HScore=" + HScore
				+ ", HPassingYear=" + HPassingYear + ", GName=" + GName + ", GBoard=" + GBoard + ", GScore=" + GScore
				+ ", GPassingYear=" + GPassingYear + ", Experience=" + Experience + ", resume="
				+ Arrays.toString(resume) + "]";
	}
	


}
