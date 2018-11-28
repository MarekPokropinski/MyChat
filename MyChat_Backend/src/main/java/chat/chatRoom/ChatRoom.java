package chat.chatRoom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import chat.basemodel.BaseModel;
import chat.message.Message;
import chat.user.User;

@Entity
@Table(name = "chatrooms")
public class ChatRoom extends BaseModel {
	@Column(unique = true)
	private String name;
	@OneToMany
	private Collection<Message> messages;

	@ManyToOne
	private User owner;

	public ChatRoom() {
	}

	public ChatRoom(String name, User owner) {
		this.name = name;
		this.owner = owner;
		this.messages = new ArrayList<>();
	}

	public ChatRoom(String name, User owner, Collection<Message> messages) {
		this(name, owner);
		this.messages = messages;
	}

	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
