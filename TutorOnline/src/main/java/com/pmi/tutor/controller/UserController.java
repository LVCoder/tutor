package com.pmi.tutor.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmi.tutor.dto.CallResponce;
import com.pmi.tutor.dto.CategoryDTO;
import com.pmi.tutor.dto.ConfirmSignUpUserDTO;
import com.pmi.tutor.dto.SignInUserDTO;
import com.pmi.tutor.dto.SignUpUserDTO;
import com.pmi.tutor.dto.UserDTO;
import com.pmi.tutor.service.CategoryService;
import com.pmi.tutor.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	 @RequestMapping(value="public/user/sign_up", method = RequestMethod.POST)
	public @ResponseBody CallResponce saveUser(@RequestBody SignUpUserDTO user){
		return userService.saveUser(user);
	}
	 
	 @RequestMapping(value="public/user/sign_in", method = RequestMethod.POST)
		public @ResponseBody UserDTO saveUser(@RequestBody SignInUserDTO user){
			return userService.autentificateUser(user);
		} 
	 @RequestMapping(value = "public/user", method = RequestMethod.GET)
	 public @ResponseBody UserDTO getUser(Principal principal){
		 return userService.getUser(principal);
	 }
	 
	 @RequestMapping(value = "public/category/get_all", method = RequestMethod.GET)
	 public @ResponseBody List<CategoryDTO> getAllCategories(){
		 return categoryService.getAllCategories();
	 }
	 
	 @RequestMapping(value = "public/user/confirm_sign_up/{token}", method = RequestMethod.POST)
	 public @ResponseBody CallResponce confirmSignUp(@RequestBody ConfirmSignUpUserDTO userDTO,@PathVariable("token") String token ){
		
		 return userService.confirmSignUp(userDTO, token);
	 }
}
