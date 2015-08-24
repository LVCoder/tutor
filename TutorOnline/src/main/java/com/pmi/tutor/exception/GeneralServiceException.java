package com.pmi.tutor.exception;

@SuppressWarnings("serial")
public class GeneralServiceException extends RuntimeException {

	public GeneralServiceException() {
		super();
	}
	
	public GeneralServiceException(Exception e) {
		super(e);
	}
	
	public GeneralServiceException(String message) {
		super(message);
	}
	
	public GeneralServiceException(String message, Exception e) {
		super(message,e);
	}
}
