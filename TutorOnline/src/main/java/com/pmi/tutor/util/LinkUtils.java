package com.pmi.tutor.util;

import java.util.Date;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class LinkUtils {
	private final static StandardPasswordEncoder ENCODER = new StandardPasswordEncoder();
	
	public static String getHashLink(){
		final Date today = new Date();
		final String link = ENCODER.encode(String.valueOf(today.getTime()));
		return link;
	}
}
