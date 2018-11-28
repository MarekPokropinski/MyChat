package chat.chatRoom;

import java.util.Optional;

import chat.user.User;

public interface ChatRoomService {
	Optional<ChatRoom> getChatRoomByName(String name);

	ChatRoom createChatRoom(String name, User owner) throws RoomException;

	void sendMessage(ChatRoom room, User owner, String content);
}
