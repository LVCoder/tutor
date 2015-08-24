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
}
