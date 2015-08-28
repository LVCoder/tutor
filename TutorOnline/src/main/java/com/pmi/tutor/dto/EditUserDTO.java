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
	private List<Long> learnSubjectsIds;
	private List<SubjectIdPricePair> teachSubjectsIdPrice;

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

	public List<Long> getLearnSubjectsIds() {
		return learnSubjectsIds;
	}

	public void setLearnSubjectsIds(List<Long> learnSubjectsIds) {
		this.learnSubjectsIds = learnSubjectsIds;
	}

	public List<SubjectIdPricePair> getTeachSubjectsIdPrice() {
		return teachSubjectsIdPrice;
	}

	public void setTeachSubjectsIdPrice(List<SubjectIdPricePair> teachSubjectsIdPrice) {
		this.teachSubjectsIdPrice = teachSubjectsIdPrice;
	}

}
