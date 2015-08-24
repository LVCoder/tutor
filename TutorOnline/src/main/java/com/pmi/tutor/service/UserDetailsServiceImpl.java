package com.pmi.tutor.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.Role;

@Service("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		com.pmi.tutor.domain.User user =  userDAO.fetchUserByEmail(email);
		User springUser = null; 
		if (user!=null){
		Set<Role> roles = user.getRoles();
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		}
		springUser = new User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
		}else {
			throw new BadCredentialsException(email);
		}
		return springUser;
	}

}
