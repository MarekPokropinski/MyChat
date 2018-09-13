package chat.user;

import java.util.Optional;

import chat.chatRoom.ChatRoomNotFoundException;

public interface UserService {
	Optional<User> getUserByUsername(String username);

	void addUserToChatRoom(User user, String chatRoomName) throws ChatRoomNotFoundException;

	void createNewUser(User user) throws UsernameNotAvailable;

	Iterable<User> allUsers();
}
