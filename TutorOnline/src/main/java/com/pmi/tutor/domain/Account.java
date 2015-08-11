package com.pmi.tutor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String surname;

	public String getName() {
		return surname;
	}

	public void setName(String name) {
		this.surname = name;
	}
	
	
	
}
