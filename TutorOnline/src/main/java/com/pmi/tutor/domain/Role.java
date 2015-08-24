package com.pmi.tutor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private RoleEnum name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}



	public enum RoleEnum{
	      ROLE_ADMIN,ROLE_TUTOR,ROLE_TUTEE, ROLE_ANONYMOUS;
	}
}
