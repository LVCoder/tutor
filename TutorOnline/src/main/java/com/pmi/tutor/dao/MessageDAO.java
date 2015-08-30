package com.pmi.tutor.dao;

import java.util.List;

import com.pmi.tutor.domain.Message;
import com.pmi.tutor.domain.User;

public interface MessageDAO extends GenericDAO<Message>{

	Long getUnreadedMessageCount(User userFrom, User userTo);

	List<Message> getMessages(User userFrom, User userTo);

}
