package com.pmi.tutor.dto;

import java.util.List;

public class EditUserDTO {

	private String firstName;

	private String lastName;

	private String username;

	private Boolean wantLearn;
	private Boolean wantTeach;
	private String experience;
	private String others;
	private String institution;
	private List<SubjectDTO> learnSubjects;
	private List<SubjectPriceDTO> teachSubjects;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getWantLearn() {
		return wantLearn;
	}

	public void setWantLearn(Boolean wantLearn) {
		this.wantLearn = wantLearn;
	}

	public Boolean getWantTeach() {
		return wantTeach;
	}

	public void setWantTeach(Boolean wantTeach) {
		this.wantTeach = wantTeach;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public List<SubjectDTO> getLearnSubjects() {
		return learnSubjects;
	}

	public void setLearnSubjects(List<SubjectDTO> learnSubjects) {
		this.learnSubjects = learnSubjects;
	}

	public List<SubjectPriceDTO> getTeachSubjects() {
		return teachSubjects;
	}

	public void setTeachSubjects(List<SubjectPriceDTO> teachSubjects) {
		this.teachSubjects = teachSubjects;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}


}
