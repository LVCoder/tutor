package com.pmi.tutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProtectedPartialsController {
	
	
	@RequestMapping(value = "/protected/partials/user_home", method = RequestMethod.GET)
	public String userHome(){
		return "public/user_home";
	}

}
