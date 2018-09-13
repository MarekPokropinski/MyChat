package chat.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import chat.basemodel.BaseModel;
import chat.chatRoom.ChatRoom;
import chat.roles.Authority;

@Entity
@Table(name = "users")
public class User extends BaseModel {
	private String username;
	private String password;
	@Email(message = "*Please provide a valid Email")
	private String email;
	private int enabled;

	@ManyToMany
	private Set<ChatRoom> chatRooms;

	@ManyToMany
	private Set<Authority> roles;

	public User() {
		chatRooms = new HashSet<>();
		roles = new HashSet<>();
		enabled = 1;
	}

	public User(String username, String password, String email) {
		this();
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<ChatRoom> getChatRooms() {
		return chatRooms;
	}

	public void setChatRooms(Set<ChatRoom> chatRooms) {
		this.chatRooms = chatRooms;
	}

	public Set<Authority> getRoles() {
		return roles;
	}

	public void setRoles(Set<Authority> roles) {
		this.roles = roles;
	}

}
