package com.pmi.tutor.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pmi.tutor.exception.GeneralServiceException;

@Service("emailService")
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	private static final String ENCODING_OPTIONS = "text/html; charset=UTF-8";
	private static final String HEADER_PARAM = "Content-Type";
	private static Logger LOGGER  = Logger.getLogger(EmailService.class);
	
	public void sendHtmlEmail(final String to, final String subject, final String body) {
		try {
			final MimeMessage message = mailSender.createMimeMessage();
			message.setHeader(HEADER_PARAM, ENCODING_OPTIONS);
			message.setFrom(new InternetAddress("Tutor_Online"));
			message.saveChanges();
			final MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(message);
		} catch (final MessagingException e) {
			LOGGER.error("to: " + to + "subject: " + subject + "\nbody: " + body);
			throw new GeneralServiceException("Error with sending HTML mail", e);
		}
	}

}
