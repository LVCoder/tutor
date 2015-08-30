package com.pmi.tutor.dao.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.MessageDAO;
import com.pmi.tutor.domain.Message;
import com.pmi.tutor.domain.User;
@Repository
public class MessageDAOImpl extends GenericDAOImpl<Message> implements
		MessageDAO {
	@Transactional
	@Override
	public Long getUnreadedMessageCount(User userFrom, User userTo) {
		return (Long) getSession()
				.createQuery(
						"select count(*) from Message m where m.userFrom= :userFrom and m.userTo = :userTo and is_readed = 0")
				.setParameter("userTo", userTo)
				.setParameter("userFrom", userFrom).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Message> getMessages(User firstUser, User secondUser){
		return  getSession()
				.createQuery(
						"from Message m where (m.userFrom= :firstUser and m.userTo = :secondUser)or(m.userFrom= :secondUser and m.userTo = :firstUser)")
				.setParameter("firstUser", firstUser)
				.setParameter("secondUser", secondUser).list();
	}
}
