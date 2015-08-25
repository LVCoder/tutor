package com.pmi.tutor.dto;

public class UserDTO {

	private Boolean anonymous;
	private Boolean enable;
	private Boolean expired;
	private String email;
	private String firtsName;
	private String lastName;
	private String username;
	private String message;

	public UserDTO(Boolean anonymous, Boolean enable, Boolean expired, String email, String firtsName, String lastName,
			String username, String message) {
		super();
		this.anonymous = anonymous;
		this.enable = enable;
		this.expired = expired;
		this.email = email;
		this.firtsName = firtsName;
		this.lastName = lastName;
		this.username = username;
		this.message = message;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirtsName() {
		return firtsName;
	}

	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
