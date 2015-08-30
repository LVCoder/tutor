package com.pmi.tutor.dto;

import java.util.List;

public class MessagingInformationDTO {
	private List<MessageDTO> messages;
	private MessageUserDTO mainUser;
	private List<MessageUserDTO> users;
	
	
	public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
	public MessageUserDTO getMainUser() {
		return mainUser;
	}
	public void setMainUser(MessageUserDTO mainUser) {
		this.mainUser = mainUser;
	}
	public List<MessageUserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<MessageUserDTO> users) {
		this.users = users;
	}
	
}
