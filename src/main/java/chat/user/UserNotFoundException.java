package chat.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not found")
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 6082099824472077469L;
	private String username;

	public UserNotFoundException(String username) {
		super();
		this.username = username;
	}

	@Override
	public String getMessage() {
		return String.format("User \"%s\" not found!", username);
	}
}
