package com.pmi.tutor.dto;

public class SubjectDTO implements Comparable<SubjectDTO>{

	private Long id;
	private String name;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(SubjectDTO o) {
		return this.getName().compareTo(o.getName());
	}

}
