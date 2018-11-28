package chat.user;

import java.util.Optional;

import chat.chatRoom.RoomException;

public interface UserService {
	Optional<User> getUserByUsername(String username);

	void addUserToChatRoom(User user, String chatRoomName) throws RoomException;

	User createNewUser(User user) throws UsernameNotAvailable;

	Iterable<User> allUsers();
}
