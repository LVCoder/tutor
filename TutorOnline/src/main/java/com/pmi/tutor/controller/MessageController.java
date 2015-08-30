package com.pmi.tutor.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmi.tutor.domain.User;
import com.pmi.tutor.dto.MessagingInformationDTO;
import com.pmi.tutor.service.MessageService;
import com.pmi.tutor.service.UserService;

@Controller
public class MessageController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@RequestMapping(value = "protected/message/information/{userId}", method = RequestMethod.GET)
	public @ResponseBody MessagingInformationDTO getMessagingInformation(@PathVariable("userId") Long userId, Principal principal) {
		User user = userService.getUser(principal.getName());
		return messageService.getMessagingInformation(user, userId);
	}
	
	
}
