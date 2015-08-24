package com.pmi.tutor.service;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.RoleDAO;
import com.pmi.tutor.dao.TemporaryLinkDAO;
import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.TemporaryLink;
import com.pmi.tutor.domain.TemporaryLink.LinkType;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.dto.CallResponce;
import com.pmi.tutor.dto.SignUpUserDTO;
import com.pmi.tutor.util.LinkUtils;

@Service("userService")
public class UserService {
	
	@Autowired
	@Qualifier("bCryptPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private TemporaryLinkDAO temporaryLinkDAO;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Value("${system.base_url}")
	private String BASE_URL;
	
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	@Transactional
	public CallResponce saveUser(SignUpUserDTO userDTO) {
		CallResponce result = validate(userDTO);
		if (result == null){
			User user = transformDTOIntoUser(userDTO);
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
			if (constraintViolations.isEmpty()){
				TemporaryLink temporaryLink = new TemporaryLink();
				temporaryLink.setCreationDate(new Date());
				temporaryLink.setIsActive(true);
				temporaryLink.setUser(user);
				temporaryLink.setLink(LinkUtils.getHashLink());
				temporaryLink.setType(LinkType.SIGN_UP_CONFIRM);
				userDAO.save(user);
				temporaryLinkDAO.save(temporaryLink);
				sendSignUpConfirmEmail(temporaryLink.getLink(), user.getEmail());
				result = new CallResponce();
				result.setMessage("We sent confirmation link to your email, please check.");
			}else {
				result = new CallResponce();
				result.setErrorMessage(constraintViolations.iterator().next().getMessage());
			}
		}
		return result;
	}
	
	private void sendSignUpConfirmEmail(String temporalLink, String email) {
		String link = BASE_URL + "/sign_up/confirm/"+temporalLink;
		String body = "<a href=\""+link+"\">Click here to confirm registration</a>";
		String subject = "Email confirmation";
		emailService.sendHtmlEmail(email, subject, body);
	}

	private User transformDTOIntoUser(SignUpUserDTO userDTO) {
		User result = new User();
		result.setEmail(userDTO.getEmail());
		result.setEnabled(false);
		result.setFirstName(userDTO.getFirtsName());
		result.setLastName(userDTO.getLastName());
		result.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		result.setUsername(userDTO.getUsername());
		return result;
	}

	private CallResponce validate(SignUpUserDTO userDTO){
		CallResponce result = null;
		if (userDTO.getPassword()==null||!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
			result = new CallResponce();
			result.setErrorMessage("Passwords do not match");
		} else if (userDAO.fetchUserByEmail(userDTO.getEmail())!=null){
			result = new CallResponce();
			result.setErrorMessage("User with such email already exist");
			
		}else if (userDAO.fetchUserByUsername(userDTO.getUsername())!=null){
			result = new CallResponce();
			result.setErrorMessage("User with such username already exist");
			
		}
        return result;
		
	}

}
