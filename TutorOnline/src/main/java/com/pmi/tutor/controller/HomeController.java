package com.pmi.tutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pmi.tutor.dao.UserDao;
import com.pmi.tutor.domain.User;


 
@Controller
public class HomeController {
	
	@Autowired
	UserDao userDao;
	String message = "Welcome to Spring MVC!";
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		User user=new User();
		user.setName(name);
		user.setSurname("hembara");
		userDao.saveUser(user);
		return mv;
	}
}