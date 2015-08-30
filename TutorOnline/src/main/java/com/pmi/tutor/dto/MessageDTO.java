package com.pmi.tutor.dto;

import java.util.Date;

public class MessageDTO {
	private String text;
	private Date creationDate;
	private Long userFromId;
	private Long userToId;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getUserFromId() {
		return userFromId;
	}
	public void setUserFromId(Long userFromId) {
		this.userFromId = userFromId;
	}
	public Long getUserToId() {
		return userToId;
	}
	public void setUserToId(Long userToId) {
		this.userToId = userToId;
	}
	
	
}
