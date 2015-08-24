package com.pmi.tutor.dao.implementation;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.User;
@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO{
	
	@Override
	@Transactional
	public User fetchUserByEmail(String email){
		try{
		return (User) getSession().createQuery("from User u where u.email=:email").setParameter("email", email).uniqueResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	@Override
	@Transactional
	public User fetchUserByUsername(String username){
		try{
		return (User) getSession().createQuery("from User u where u.username=:username").setParameter("username", username).uniqueResult();
		}catch(NoResultException e){
			return null;
		}
	}
}
