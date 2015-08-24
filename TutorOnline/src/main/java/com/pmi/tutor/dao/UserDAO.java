package com.pmi.tutor.dao;

import com.pmi.tutor.domain.User;

public interface UserDAO extends GenericDAO<User> {

	User fetchUserByEmail(String email);

	User fetchUserByUsername(String username);

}
