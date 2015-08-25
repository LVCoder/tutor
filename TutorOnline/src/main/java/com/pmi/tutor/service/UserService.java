package com.pmi.tutor.service;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.RoleDAO;
import com.pmi.tutor.dao.TemporaryLinkDAO;
import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.Role;
import com.pmi.tutor.domain.TemporaryLink;
import com.pmi.tutor.domain.TemporaryLink.LinkType;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.dto.CallResponce;
import com.pmi.tutor.dto.SignInUserDTO;
import com.pmi.tutor.dto.SignUpUserDTO;
import com.pmi.tutor.dto.UserDTO;
import com.pmi.tutor.util.LinkUtils;

@Service("userService")
public class UserService {

	@Autowired
	@Qualifier("bCryptPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
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

	private static Logger LOGGER = Logger.getLogger(UserService.class);
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@Transactional
	public CallResponce saveUser(SignUpUserDTO userDTO) {
		CallResponce result = validate(userDTO);
		if (result == null) {
			User user = transformDTOIntoUser(userDTO);
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
			if (constraintViolations.isEmpty()) {
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
			} else {
				result = new CallResponce();
				result.setErrorMessage(constraintViolations.iterator().next().getMessage());
			}
		}
		return result;
	}

	private void sendSignUpConfirmEmail(String temporalLink, String email) {
		String link = BASE_URL + "/sign_up/confirm/" + temporalLink;
		String body = "<a href=\"" + link + "\">Click here to confirm registration</a>";
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

	private CallResponce validate(SignUpUserDTO userDTO) {
		CallResponce result = null;
		if (userDTO.getPassword() == null || !userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
			result = new CallResponce();
			result.setErrorMessage("Passwords do not match");
		} else if (userDAO.fetchUserByEmail(userDTO.getEmail()) != null) {
			result = new CallResponce();
			result.setErrorMessage("User with such email already exist");

		} else if (userDAO.fetchUserByUsername(userDTO.getUsername()) != null) {
			result = new CallResponce();
			result.setErrorMessage("User with such username already exist");

		}
		return result;

	}

	public UserDTO autentificateUser(SignInUserDTO user) {
		final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getEmail().trim(), user.getPassword());
		Authentication authentication = null;
		try {
			authentication = this.authManager.authenticate(authenticationToken);

		} catch (final DisabledException e) {
			LOGGER.debug("Failed to authenticate : " + user.getEmail());
			return new UserDTO("Please confirm your sign up using link in your email", true, true);
		} catch (final BadCredentialsException e) {
			LOGGER.debug("Failed to authenticate : " + user.getEmail());
			return new UserDTO( "Failed to obtain authentication, please check your credentials", true,
					true);
		} catch (final AuthenticationException e) {
			LOGGER.debug("Failed to authenticate : " + user.getEmail());
			return new UserDTO( "Failed to authenticate, please check your credentials", true, true);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return transformAuthentcationToUserDTO(authentication);
	}
	
	@Transactional
	private UserDTO transformAuthentcationToUserDTO(final Authentication authentication) {
		UserDTO userDTO = null;
		if (authentication != null) {
			final Object principal = authentication.getPrincipal();
			if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
				return new UserDTO( "Anonymous", false, true);
			}
			final UserDetails userDetails = (UserDetails) principal;
			final User user = userDAO.fetchUserByEmail(userDetails.getUsername());
			

			if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.RoleEnum.ROLE_ANONYMOUS.toString()))) {
				if (!userDetails.isEnabled()) {
					userDTO = new UserDTO();
					userDTO.setEmail(userDetails.getUsername());
					userDTO.setRoles(createRoleMap(userDetails));
					userDTO.setAnonymous(false);
					userDTO.setEnabled(userDetails.isEnabled());
					userDTO.setMessage("Please, confirm your sign up");
					userDTO.setFirstName(user.getFirstName());
					userDTO.setLastName(user.getLastName());
					
					return userDTO;
				}
			} 
			userDTO = new UserDTO();
			userDTO.setEmail(userDetails.getUsername());
			userDTO.setRoles(createRoleMap(userDetails));
			userDTO.setAnonymous(false);
			userDTO.setEnabled(userDetails.isEnabled());
			userDTO.setMessage("Success");
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			
			return userDTO;
		}
		userDTO= new UserDTO();
		userDTO.setMessage("Failed to obtain authentication, please check your credentials");
		userDTO.setEnabled(false);
		userDTO.setAnonymous(false);
		return userDTO;
	}
	
	@Transactional
	public UserDTO getUser(final Principal principal) {
		UserDTO userDTO = new UserDTO();
		if (principal != null) {
			final User user = userDAO.fetchUserByEmail(principal.getName());
			if (user != null) {
				
						userDTO = new UserDTO();
						userDTO.setEmail(user.getEmail());
						userDTO.setAnonymous(false);
						userDTO.setEnabled(true);
						userDTO.setRoles(createRoleMap(user));
						userDTO.setFirstName(user.getFirstName());
						userDTO.setLastName(user.getLastName());
						
						return userDTO;
					
				} 

			return new UserDTO( "Anonymous", false, true);
		}
		return new UserDTO("Anonymous", false, true);
	}
	
	private static Map<String, Boolean> createRoleMap(final User user) {
		final Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (final Role role : user.getRoles()) {
			roles.put(role.getName().toString(), Boolean.TRUE);
		}
		return roles;

	}

	private static Map<String, Boolean> createRoleMap(final UserDetails userDetails) {
		final Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (final GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}


}
