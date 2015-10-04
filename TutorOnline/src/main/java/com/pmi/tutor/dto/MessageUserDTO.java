package com.pmi.tutor.dto;


public class MessageUserDTO {
	private String username;
	private Long userId;
	private Long unreadedMessageQuantity;
	private String imagePath;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUnreadedMessageQuantity() {
		return unreadedMessageQuantity;
	}
	public void setUnreadedMessageQuantity(Long unreadedMessageQuantity) {
		this.unreadedMessageQuantity = unreadedMessageQuantity;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	

	
	
}
