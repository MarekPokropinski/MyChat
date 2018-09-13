package chat.message;

import chat.user.User;

public interface MessageService {
	Message createMessage(User author, String content);
}
