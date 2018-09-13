package chat.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username not available")
public class UsernameNotAvailable extends Exception {
	private static final long serialVersionUID = 6082099824472077469L;
	private String username;

	public UsernameNotAvailable(String username) {
		super();
		this.username = username;
	}

	@Override
	public String getMessage() {
		return String.format("Username \"%s\" not available !", username);
	}
}
