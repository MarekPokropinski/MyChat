package chat.chatRoom;

import java.util.Optional;

public interface ChatRoomService {
	Optional<ChatRoom> getChatRoomByName(String name);
}
