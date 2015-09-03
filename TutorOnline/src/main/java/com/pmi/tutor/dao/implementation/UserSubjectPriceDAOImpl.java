package com.pmi.tutor.dao.implementation;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.UserSubjectPriceDAO;
import com.pmi.tutor.domain.Subject;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.domain.UserSubjectPrice;
@Repository
public class UserSubjectPriceDAOImpl extends GenericDAOImpl<UserSubjectPrice> implements UserSubjectPriceDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserSubjectPrice> findByUser(User user){
		return getSession().createQuery("from UserSubjectPrice usp where usp.user = :user").setParameter("user", user).list();
	}
	
	@Override
	@Transactional
	public UserSubjectPrice findByUserAndSubject(User user, Subject subject){
		try{
		return (UserSubjectPrice) getSession().createQuery("from UserSubjectPrice usp where usp.user = :user and usp.subject = :subject").setParameter("user", user).setParameter("subject", subject).uniqueResult();
		} catch (NoResultException e){
			return null;
		}
	}
}
