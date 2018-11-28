package chat.chatRoom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.message.MessageService;
import chat.user.User;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	private ChatRoomRepository chatRoomRepository;
	private MessageService messageService;

	@Autowired
	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, MessageService messageService) {
		this.chatRoomRepository = chatRoomRepository;
		this.messageService = messageService;
	}

	@Override
	public Optional<ChatRoom> getChatRoomByName(String name) {
		return chatRoomRepository.findByName(name);
	}

	@Override
	public ChatRoom createChatRoom(String name, User owner) throws RoomException {
		if (!getChatRoomByName(name).isPresent()) {
			ChatRoom room = new ChatRoom(name, owner);
			chatRoomRepository.save(room);
			return room;
		} else {
			throw RoomException.roomNotFound;
		}
	}

	@Override
	public void sendMessage(ChatRoom room, User owner, String content) {
		room.getMessages().add(messageService.createMessage(owner, content));
		chatRoomRepository.save(room);
	}

}
