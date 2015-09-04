package com.pmi.tutor.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pmi.tutor.domain.User;
import com.pmi.tutor.dto.AutocompleteDTO;
import com.pmi.tutor.dto.CallResponce;
import com.pmi.tutor.dto.CategoryDTO;
import com.pmi.tutor.dto.ConfirmSignUpUserDTO;
import com.pmi.tutor.dto.EditUserDTO;
import com.pmi.tutor.dto.SignInUserDTO;
import com.pmi.tutor.dto.SignUpUserDTO;
import com.pmi.tutor.dto.UserDTO;
import com.pmi.tutor.service.CategoryService;
import com.pmi.tutor.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "public/user/sign_up", method = RequestMethod.POST)
	public @ResponseBody CallResponce saveUser(@RequestBody SignUpUserDTO user) {
		return userService.saveUser(user);
	}

	@RequestMapping(value = "public/user/sign_in", method = RequestMethod.POST)
	public  UserDTO saveUser(@RequestBody SignInUserDTO user) {
		return userService.autentificateUser(user);
	}

	@RequestMapping(value = "public/user", method = RequestMethod.GET)
	public UserDTO getUser(Principal principal) {
		return userService.getUser(principal);
	}

	@RequestMapping(value = "public/category/get_all", method = RequestMethod.GET)
	public @ResponseBody List<CategoryDTO> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@RequestMapping(value = "public/user/confirm_sign_up/{token}", method = RequestMethod.POST)
	public @ResponseBody CallResponce confirmSignUp(
			@RequestBody ConfirmSignUpUserDTO userDTO,
			@PathVariable("token") String token) {

		return userService.confirmSignUp(userDTO, token);
	}

	@RequestMapping(value = "public/user/avatar/save/{token}", method = RequestMethod.POST)
	public @ResponseBody CallResponce saveAvatarWithToken(
			@RequestParam(value = "file", required = false) final MultipartFile file,
			@PathVariable("token") String token) {
		return userService.saveAvatabWithToken(file,token);
	}
	
	@RequestMapping(value = "public/institution/autocomplete", method = RequestMethod.GET, params={"query"})
	public @ResponseBody AutocompleteDTO getInstitutionAutocomplete(@RequestParam(value = "query", required = false) String regexp ) {
		return userService.getInstitutionByRegexp(regexp);
	}

	@RequestMapping(value = "protected/user/get", method = RequestMethod.GET)
	public @ResponseBody EditUserDTO getEditUser(Principal principal) {
		User user = userService.getUser(principal.getName());
		return userService.getEditUser(user);
	}
	@RequestMapping(value = "protected/user/edit", method=RequestMethod.POST)
	public CallResponce editUser(@RequestBody EditUserDTO editUserDTO, Principal principal){
		User user = userService.getUser(principal.getName());
		return userService.editUser(editUserDTO, user);
	}
	
	@RequestMapping(value = "public/password/forgot", method=RequestMethod.POST)
	public CallResponce editUser(@RequestBody UserDTO user){
	
		return userService.sendForgotPassword(user.getEmail());
	}
	
	@RequestMapping(value = "public/password/change/{token}", method=RequestMethod.POST)
	public CallResponce editUser(@RequestBody SignUpUserDTO user, @PathVariable("token") String token ){
	
		return userService.changePassword(user, token);
	}
	
	@RequestMapping(value = "protected/user/avatar/change", method=RequestMethod.POST)
	public void editUser(@RequestParam(value = "file", required = false) final MultipartFile file,Principal principal ){
		User user = userService.getUser(principal.getName());
		userService.updateUserAvatar(file,user);
	}
	
	@RequestMapping(value = "protected/user/search", method=RequestMethod.GET, params={"query"})
	public List<UserDTO> searchUsers(@RequestParam("query") String query ){
	
		return userService.searchUsers(query);
	}
	
	@RequestMapping(value = "protected/user/logout", method=RequestMethod.POST)
	public void logout(){
	
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	
}
