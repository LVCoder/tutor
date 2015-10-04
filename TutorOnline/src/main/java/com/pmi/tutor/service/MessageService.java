package com.pmi.tutor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.MessageDAO;
import com.pmi.tutor.dao.UserDAO;
import com.pmi.tutor.domain.Message;
import com.pmi.tutor.domain.User;
import com.pmi.tutor.dto.MessageDTO;
import com.pmi.tutor.dto.MessageUserDTO;
import com.pmi.tutor.dto.MessagingInformationDTO;

@Service("messageService")
public class MessageService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MessageDAO messageDAO;

	@Transactional
	public MessagingInformationDTO getMessagingInformation(User user,
			Long userId) {
		MessagingInformationDTO result = new MessagingInformationDTO();
		User mainUser = userDAO.fetchById(User.class, userId);
		if (mainUser == null) {
			mainUser = userDAO.findMainUser(user);
		}
		if (mainUser != null) {
			MessageUserDTO mainUserDTO = new MessageUserDTO();
			mainUserDTO.setUserId(mainUser.getId());
			mainUserDTO.setUsername(mainUser.getUsername());
			mainUserDTO.setImagePath(mainUser.getAvatarPath());
			result.setMainUser(mainUserDTO);
			List<Message> messages = messageDAO.getMessages(
					user, mainUser);
			setMessagesUnreadedFalse(messages);
			result.setMessages(transformMessagesToDTO(messages));
			List<User> interlocutors = userDAO.getInterlocutors(user);
			List<MessageUserDTO> users = new ArrayList<MessageUserDTO>();
			for (User u : interlocutors) {
				users.add(transformUserToDTO(user, u));
			}
			result.setUsers(users);
		}
		return result;
	}

	private void setMessagesUnreadedFalse(List<Message> messages) {
		for (Message message: messages){
			message.setIsReaded(true);
			messageDAO.update(message);
		}
		
	}

	private MessageUserDTO transformUserToDTO(User firstUser, User secondUser) {
		MessageUserDTO result = new MessageUserDTO();
		result.setUserId(secondUser.getId());
		result.setUsername(secondUser.getUsername());
		result.setUnreadedMessageQuantity(messageDAO.getUnreadedMessageCount(
				firstUser, secondUser));
		result.setImagePath(secondUser.getAvatarPath());
		return result;

	}

	private List<MessageDTO> transformMessagesToDTO(List<Message> mesasges) {
		List<MessageDTO> result = new ArrayList<MessageDTO>();
		for (Message message : mesasges) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setCreationDate(message.getCreationDate());
			messageDTO.setText(message.getText());
			messageDTO.setUserFromId(message.getUserFrom().getId());
			messageDTO.setUserToId(message.getUserTo().getId());
			result.add(messageDTO);
		}

		return result;
	}

	public void saveMessage(MessageDTO messageDTO) {
		if (messageDTO != null && isValid(messageDTO)) {
			Message message = new Message();
			message.setText(messageDTO.getText());
			message.setCreationDate(new Date());
			message.setIsReaded(false);
			message.setUserFrom(userDAO.fetchById(User.class,
					messageDTO.getUserFromId()));
			message.setUserTo(userDAO.fetchById(User.class,
					messageDTO.getUserToId()));
			messageDAO.save(message);
		}

	}

	private boolean isValid(MessageDTO message) {
		if (message.getText() == null || message.getText().trim().isEmpty()
				|| message.getUserFromId() == null
				|| message.getUserToId() == null) {
			return false;
		}
		return true;
	}

}
