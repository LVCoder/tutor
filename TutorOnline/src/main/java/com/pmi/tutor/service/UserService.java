package com.pmi.tutor.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.pmi.tutor.dao.RoleDAO;
import com.pmi.tutor.dao.SubjectDAO;
import com.pmi.tutor.dao.TemporaryLinkDAO;
import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.dao.UserSubjectPriceDAO;
import com.pmi.tutor.domain.Role;
import com.pmi.tutor.domain.Role.RoleEnum;
import com.pmi.tutor.domain.Subject;
import com.pmi.tutor.domain.TemporaryLink;
import com.pmi.tutor.domain.TemporaryLink.LinkType;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.domain.UserSubjectPrice;
import com.pmi.tutor.dto.AutocompleteDTO;
import com.pmi.tutor.dto.CallResponce;
import com.pmi.tutor.dto.ConfirmSignUpUserDTO;
import com.pmi.tutor.dto.SignInUserDTO;
import com.pmi.tutor.dto.SignUpUserDTO;
import com.pmi.tutor.dto.SubjectIdPricePair;
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
	
	@Autowired
	private SubjectDAO subjectDAO;
	
	@Autowired
	private UserSubjectPriceDAO userSubjectPriceDAO;

	@Value("${system.base_url}")
	private String BASE_URL;

	private static final String IMAGE_PATH = "/Tutor/images/avatars/";
	
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
		String link = BASE_URL + "/confirm_sign_up/" + temporalLink;
		String body = "Here is a for confirmation your account <br>"+link;
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
					userDTO.setUsername(user.getUsername());
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
			userDTO.setUsername(user.getUsername());
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
						userDTO.setId(user.getId());
						userDTO.setEmail(user.getEmail());
						userDTO.setAnonymous(false);
						userDTO.setEnabled(true);
						userDTO.setRoles(createRoleMap(user));
						userDTO.setFirstName(user.getFirstName());
						userDTO.setLastName(user.getLastName());
						userDTO.setUsername(user.getUsername());
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

	@Transactional
	public CallResponce confirmSignUp(ConfirmSignUpUserDTO userDTO, String token) {
		CallResponce result = new CallResponce();
		if (userDTO!=null&&token!=null){
			TemporaryLink temporaryLink = temporaryLinkDAO.fetchByLink(token);
			if (temporaryLink!=null&&temporaryLink.getIsActive()){
				User user = temporaryLink.getUser();
				if (userDTO.getWantLearn()){
					Role role = roleDAO.fetchOrCreateRoleByName(RoleEnum.ROLE_TUTEE);
					user.getRoles().add(role);
					user.setInstitution(userDTO.getInstitution());
					List<Long> learnSubjectIds = userDTO.getLearnSubjectsIds();
					if (learnSubjectIds!=null&&!learnSubjectIds.isEmpty()){
						for (Long id:learnSubjectIds){
							Subject subject = subjectDAO.fetchById(Subject.class, id);
							if (subject!=null){
								user.getSubjects().add(subject);
								
							}
						}
					}
				}
				if (userDTO.getWantTeach()){
					Role role = roleDAO.fetchOrCreateRoleByName(RoleEnum.ROLE_TUTOR);
					user.getRoles().add(role);
					List<SubjectIdPricePair> teachSubjects = userDTO.getTeachSubjectsIdPrice();
					if (teachSubjects!=null&&!teachSubjects.isEmpty()){
						for (SubjectIdPricePair teachSubject:teachSubjects){
							Subject subject = subjectDAO.fetchById(Subject.class, teachSubject.getSubjectId());
							if (subject!=null){
								UserSubjectPrice userSubjectPrice = new UserSubjectPrice();
								userSubjectPrice.setSubject(subject);
								userSubjectPrice.setUser(user);
								userSubjectPrice.setPrice(teachSubject.getPrice());
								userSubjectPriceDAO.save(userSubjectPrice);
							}
						}
					}
					user.setExperience(userDTO.getExperience());
					user.setOthers(userDTO.getOthers());
				}
				user.setEnabled(true);
				userDAO.update(user);
				temporaryLink.setIsActive(false);
				temporaryLinkDAO.update(temporaryLink);
				result.setMessage("Success");
			} else {
				result.setErrorMessage("Wrong link");
			}
		} else {
			result.setErrorMessage("Incorect data");
		}
		return result;
	}
@Transactional
	public CallResponce saveAvatabWithToken(MultipartFile file, String token) {
		CallResponce result =null;
		if (file!=null&&token!=null){
			TemporaryLink temporaryLink = temporaryLinkDAO.fetchByLink(token);
			if (temporaryLink!=null&&temporaryLink.getIsActive()){
				User user = temporaryLink.getUser();
				updateUserAvatar(file, user);
				result = new CallResponce();
				result.setMessage("Success");
			}
		}
		if (result== null){
			result = new CallResponce();
			result.setErrorMessage("Error");
		}
		return result;
	}
	private  void updateUserAvatar(final MultipartFile file, final User user) {
		if (file != null) {
			if (!file.isEmpty()) {
				String imagePath = null;

				try {
					final String filePath = System.getProperty("catalina.home");
					imagePath = user.getAvatarPath();
					if (imagePath == null || imagePath.isEmpty()) {
						imagePath = IMAGE_PATH + UUID.randomUUID() + ".png";
						user.setAvatarPath(imagePath);
						userDAO.update(user);
					}
					if (imagePath != "") {

						final byte[] bytes = file.getBytes();

						final File imageDirectory = new File(filePath + IMAGE_PATH);
						if (!imageDirectory.exists()) {
							imageDirectory.mkdirs();
						}
						final File resultFile = new File(filePath + user.getAvatarPath());
						if (!resultFile.exists()) {
							resultFile.createNewFile();
						}

						final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(resultFile));
						stream.write(bytes);
						stream.close();
					} else {

					}
				} catch (final Exception e) {
					LOGGER.warn("Failed to write file", e);
				}
			}
		} else {
			final File resultFile = new File(System.getProperty("catalina.home") + user.getAvatarPath());
			if (resultFile.exists()) {
				resultFile.delete();
			}
			user.setAvatarPath(null);
		}
	}

	public AutocompleteDTO getInstitutionByRegexp(String regexp) {
		AutocompleteDTO result = new AutocompleteDTO();
		if (regexp!=null&&!regexp.isEmpty()){
			result.setQuery(regexp);
			result.setSuggestions(userDAO.getInstitutionsByRegexp(regexp));
		}
		return result;
	}

	public User getUser(String email) {
		return userDAO.fetchUserByEmail(email);
	}

}
