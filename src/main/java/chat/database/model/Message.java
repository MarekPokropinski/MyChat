package chat.database.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
	private User author;
	private String content;
	private LocalDate date;
}
