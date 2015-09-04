package com.pmi.tutor.dao.implementation;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	@Override
	@Transactional
	public User fetchUserByEmail(String email) {
		try {
			return (User) getSession()
					.createQuery("from User u where u.email=:email")
					.setParameter("email", email).uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public User fetchUserByUsername(String username) {
		try {
			return (User) getSession()
					.createQuery("from User u where u.username=:username")
					.setParameter("username", username).uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getInstitutionsByRegexp(String regexp) {
		return getSession()
				.createSQLQuery(
						"SELECT DISTINCT institution FROM user WHERE institution REGEXP :regexp")
				.setParameter("regexp", regexp).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getInterlocutors(User user) {
		return getSession()
				.createQuery(
						"select distinct u from User u, Message m where (m.userFrom=u and m.userTo=:user)or(m.userTo=u and m.userFrom=:user)")
				.setParameter("user", user).list();
	}
	@Override
	@Transactional
	public User findMainUser(User user){
		return (User) getSession().createQuery("select distinct u from User u , Message m where u!=:user and (m.userFrom=u or m.userTo = u) and m.creationDate = (select max(me.creationDate) from Message me where me.userTo = :user or  me.userFrom = :user)").setParameter("user", user).uniqueResult();
		
	}
}
