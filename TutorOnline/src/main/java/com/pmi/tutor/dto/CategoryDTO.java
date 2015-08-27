package com.pmi.tutor.dto;

import java.util.List;

public class CategoryDTO implements Comparable<CategoryDTO>{

	private String name;
	private List<SubjectDTO> subjects;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubjectDTO> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
	@Override
	public int compareTo(CategoryDTO o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
