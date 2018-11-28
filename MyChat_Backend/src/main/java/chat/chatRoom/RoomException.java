package chat.chatRoom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomException extends Exception {
	private static final long serialVersionUID = -4220470240950873545L;
	public static final RoomException roomNotFound = new RoomException("Room not found");
	public static final RoomException userNotAllowed = new RoomException("This user is not allowed to this room");
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RoomException(String message) {
		this.message = message;
	}
}
