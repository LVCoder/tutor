package com.pmi.tutor.dto;

public class CallResponce {

	private String message;
	private String errorMessage;
	
	public CallResponce(){
		
	}
	
	public CallResponce(String message, String errorMessage){
		this.message = message;
		this.errorMessage = errorMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
