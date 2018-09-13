package chat.message;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.user.User;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepository;

	@Autowired
	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public Message createMessage(User author, String content) {
		return messageRepository.save(new Message(author, content, LocalDate.now()));
	}

}
