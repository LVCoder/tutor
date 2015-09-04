package com.pmi.tutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmi.tutor.service.UserService;

@Controller
public class ProtectedPartialsController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/protected/partials/user_home", method = RequestMethod.GET)
	public String userHome() {
		return "public/user_home";
	}

	@RequestMapping(value = "/protected/user/edit_profile", method = RequestMethod.GET)
	public String editProfile() {
		return "public/edit_profile";
	}

	@RequestMapping(value = "/protected/partials/messaging", method = RequestMethod.GET)
	public String plainIndex() {
		return "protected/messaging";
	}
	
	@RequestMapping(value = "/protected/partials/search", method = RequestMethod.GET)
	public String search() {
		return "protected/search";
	}
	
	@RequestMapping(value = "protected/partials/header", method = RequestMethod.GET)
	public String getHeader(){
		return "protected/header";
	}

}
