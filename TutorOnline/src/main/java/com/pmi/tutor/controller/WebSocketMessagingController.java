package com.pmi.tutor.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.pmi.tutor.dto.MessageDTO;
import com.pmi.tutor.service.MessageService;
import com.pmi.tutor.service.UserService;

@Controller
public class WebSocketMessagingController {
	private SimpMessagingTemplate template;
	 
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	public WebSocketMessagingController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/send_message")
	public void greeting(MessageDTO message,Principal principal) throws Exception {
		if (message!=null && message.getUserFromId()!=message.getUserToId()){
    messageService.saveMessage(message);
	template.convertAndSend("/client/get_message"+message.getUserToId(),message );
		}
	}

}
