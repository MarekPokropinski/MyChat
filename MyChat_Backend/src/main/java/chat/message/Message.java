package chat.message;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import chat.basemodel.BaseModel;
import chat.user.User;

@Entity
@Table(name = "messages")
public class Message extends BaseModel {
	@ManyToOne
	private User author;
	private String content;
	private LocalDate date;

	public Message(User author, String content, LocalDate date) {
		this.author = author;
		this.content = content;
		this.date = date;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
