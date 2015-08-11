package com.pmi.tutor.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.pmi.tutor.domain.User;

@Transactional
@Repository("userDao")
public class UserDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void saveUser(User user){
		sessionFactory.getCurrentSession().save(user);
		
	}

	public User getUser(Long id) {
		User file = new User();
		Session session = null;

		session = sessionFactory.openSession();

		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", id.intValue()));
		List<User> results = cr.list();
		session.close();
		if (results.isEmpty())
			return null;
		else
			file = results.get(0);
		
		return file;
		
	}

}
