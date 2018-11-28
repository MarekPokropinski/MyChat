package chat.chatRoom;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chat.user.User;
import chat.user.UserService;

@RestController
@RequestMapping("/room")
public class ChatRoomController {
	private static final String roomNotFoundMessage = "Room does not exist";
	private static final Logger LOG = Logger.getLogger(ChatRoomController.class);
	private ChatRoomService chatRoomService;
	private UserService userService;

	@Autowired
	public ChatRoomController(ChatRoomService chatRoomService, UserService userService) {
		this.chatRoomService = chatRoomService;
		this.userService = userService;
	}

	@GetMapping("/messages")
	List<String> getMessages(@RequestParam String roomName) throws RoomException {
		ChatRoom room = chatRoomService.getChatRoomByName(roomName)
				.orElseThrow(() -> new RoomException(roomNotFoundMessage));
		return room.getMessages().stream().map(message -> message.toString()).collect(Collectors.toList());
	}

	@PostMapping("/send")
	void sendMessage(Principal user, @RequestParam String roomName, @RequestBody String messageContent)
			throws RoomException {
		LOG.info("hello");
		ChatRoom room = chatRoomService.getChatRoomByName(roomName)
				.orElseThrow(() -> new RoomException(roomNotFoundMessage));
		User author = userService.getUserByUsername(user.getName()).orElseThrow(() -> RoomException.userNotAllowed);
		// User author = userService.getUserByUsername("admin").orElseThrow(() ->
		// RoomException.userNotAllowed);
		chatRoomService.sendMessage(room, author, messageContent);
	}
}
