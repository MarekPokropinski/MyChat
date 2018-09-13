package chat.chatRoom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Chat room not found")
public class ChatRoomNotFoundException extends Exception {
	private static final long serialVersionUID = 6082099824472077469L;
	private String username;

	public ChatRoomNotFoundException(String username) {
		super();
		this.username = username;
	}

	@Override
	public String getMessage() {
		return String.format("Chat room \"%s\" not found!", username);
	}
}
