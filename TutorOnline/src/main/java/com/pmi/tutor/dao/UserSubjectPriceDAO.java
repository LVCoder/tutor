package com.pmi.tutor.dao;

import java.util.List;

import com.pmi.tutor.domain.Subject;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.domain.UserSubjectPrice;

public interface UserSubjectPriceDAO extends GenericDAO<UserSubjectPrice>{

	List<UserSubjectPrice> findByUser(User user);

	UserSubjectPrice findByUserAndSubject(User user, Subject subject);

	
}
