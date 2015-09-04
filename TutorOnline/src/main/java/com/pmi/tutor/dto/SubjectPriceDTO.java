package com.pmi.tutor.dto;

public class SubjectPriceDTO {
	private SubjectDTO subject;
	private Double price;

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
