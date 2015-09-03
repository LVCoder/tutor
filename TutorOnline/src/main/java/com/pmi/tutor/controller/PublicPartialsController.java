package com.pmi.tutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublicPartialsController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String plainIndex(){
		return "public/index";
	}
	
	@RequestMapping(value = "public/partials/index", method = RequestMethod.GET)
	public String index(){
		return "public/index";
	}
	
	@RequestMapping(value = "public/partials/home", method = RequestMethod.GET)
	public String home(){
		return "public/home";
	}
	
	@RequestMapping(value = "public/partials/sign_up", method = RequestMethod.GET)
	public String signUp(){
		return "public/sign_up";
	}
	
	@RequestMapping(value = "public/partials/sign_in", method = RequestMethod.GET)
	public String signIn(){
		return "public/sign_in";
	}
	@RequestMapping(value = "public/partials/confirm_sign_up", method = RequestMethod.GET)
	public String confirmSignUp(){
		return "public/confirm_sign_up";
	}
	
	@RequestMapping(value = "public/partials/forgot_password", method = RequestMethod.GET)
	public String forgotPassword(){
		return "public/forgot_password";
	}
	
	@RequestMapping(value = "public/partials/change_password", method = RequestMethod.GET)
	public String changePassword(){
		return "public/change_password";
	}
}
