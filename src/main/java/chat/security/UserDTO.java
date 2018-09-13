package chat.security;

public class UserDTO {
	private String username;
	private String password;
	private int enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format("username: %s\npassword: %s\nenabled: %d\n", username, password, enabled);
	}
}
