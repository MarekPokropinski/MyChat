package chat.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chat.chatRoom.ChatRoomNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {
	private UserService userService;
	private HttpServletRequest httpServletRequest;

	@Autowired
	public UserController(UserService userService, HttpServletRequest httpServletRequest) {
		this.userService = userService;
		this.httpServletRequest = httpServletRequest;
	}

	@GetMapping("/empty")
	String empty() {
		return "empty";
	}

	@GetMapping("/test")
	String test() {
		return httpServletRequest.getRemoteUser();
	}

	@GetMapping("/all")
	String all() {
		StringBuilder users = new StringBuilder();
		userService.allUsers().forEach(user -> users.append(String.format("User %d\nusername: %s\npassword: %s\n\n",
				user.getId(), user.getUsername(), user.getPassword())));
		return users.toString();
	}

	@GetMapping("/rooms")
	List<String> getUserRooms(@RequestParam String username) throws UserNotFoundException {
		User user = userService.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		return user.getChatRooms().stream().map(room -> room.getName()).collect(Collectors.toList());
	}

	@PutMapping("/addToRoom")
	void addUserToRoom(@RequestParam String username, @RequestParam String chatRoomName)
			throws UserNotFoundException, ChatRoomNotFoundException {
		User user = userService.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		userService.addUserToChatRoom(user, chatRoomName);
	}

	@PostMapping("/register")
	void createNewUser(@RequestBody UserDTO userDto) throws UsernameNotAvailable {
		userService.createNewUser(new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail()));
	}
}
