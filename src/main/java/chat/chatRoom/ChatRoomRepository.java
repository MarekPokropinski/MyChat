package chat.chatRoom;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
	Optional<ChatRoom> findByName(String name);
}
