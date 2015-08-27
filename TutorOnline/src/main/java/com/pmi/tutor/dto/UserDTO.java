package com.pmi.tutor.dto;

import java.util.Map;

public class UserDTO {

	private Boolean anonymous;
	private Boolean enabled;
	private String email;
	private String firstName;
	private String lastName;
	private String username;
	private String message;
	private Map<String, Boolean> roles;

	public UserDTO(){};
	
	public UserDTO(String message,Boolean enable, Boolean anonymous) {
		super();
		this.anonymous = anonymous;
		this.enabled = enable;
		this.message = message;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}


	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public Map<String, Boolean> getRoles() {
		return roles;
	}



	public void setRoles(Map<String, Boolean> roles) {
		this.roles = roles;
	}

}
