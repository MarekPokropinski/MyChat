package chat.chatRoom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	private ChatRoomRepository chatRoomRepository;

	@Autowired
	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}

	@Override
	public Optional<ChatRoom> getChatRoomByName(String name) {
		return chatRoomRepository.findByName(name);
	}

}
