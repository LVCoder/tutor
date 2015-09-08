package com.pmi.tutor.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "user")
public class User {

	public User() {
		registrationDate = new Date();

	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Size(min = 2, max = 35, message = "First name should contains from {min} to {max} symbols")
	@Column(name = "first_name")
	private String firstName;

	@Length(min = 2, max = 35, message = "Last name should contains from {min} to {max} symbols")
	@Column(name = "lastName")
	private String lastName;

	@Email(message = "Wrong email format")
	@Column(name = "email", unique = true)
	private String email;

	@Length(min = 6, max = 100, message = "Password should contains from {min} to {max} symbols")
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "social_id")
	private String socialId;
	
	@Length(min = 2, max = 35, message = "Last name should contains from {min} to {max} symbols")
	@Column(name = "username",unique = true)
	private String username;
	
	@Column(name = "hourly_rate")
	private Double hourlyRate;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "others")
	private String others;
	
	@Column(name = "avatar_path")
	private String avatarPath;
	
	@Column(name = "institution")
	private String institution;
	
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<Role>();
	
	@ManyToMany(targetEntity = Subject.class, fetch = FetchType.EAGER)
	private Set<Subject> subjects = new HashSet<Subject>();
	
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}





	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
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

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	

}
