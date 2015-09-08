package com.pmi.tutor.dao;

import java.util.List;

import com.pmi.tutor.domain.User;

public interface UserDAO extends GenericDAO<User> {

	User fetchUserByEmail(String email);

	User fetchUserByUsername(String username);

	List<String> getInstitutionsByRegexp(String regexp);

	List<User> getInterlocutors(User user);

	User findMainUser(User user);

	List<User> findUserByOneRegexp(String regexp);

	List<User> findUserByTwoRegexp(String regexpOne, String regexpTwo);

	User fetchUserBySocialId(String socialId);

}
