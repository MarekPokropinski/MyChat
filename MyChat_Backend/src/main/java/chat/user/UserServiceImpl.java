package chat.user;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chat.chatRoom.ChatRoom;
import chat.chatRoom.ChatRoomNotFoundException;
import chat.chatRoom.ChatRoomService;
import chat.roles.RolesService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ChatRoomService chatRoomService;
	private PasswordEncoder passwordEncoder;
	private RolesService rolesService;

	private static final Logger LOG = Logger.getLogger(UserService.class);

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ChatRoomService chatRoomService,
			PasswordEncoder passwordEncoder, RolesService rolesService) {
		this.userRepository = userRepository;
		this.chatRoomService = chatRoomService;
		this.passwordEncoder = passwordEncoder;
		this.rolesService = rolesService;
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void addUserToChatRoom(User user, String chatRoomName) throws ChatRoomNotFoundException {
		var chatRooms = user.getChatRooms();
		ChatRoom chatRoom = chatRoomService.getChatRoomByName(chatRoomName)
				.orElseThrow(() -> new ChatRoomNotFoundException(chatRoomName));

		chatRooms.add(chatRoom);
		userRepository.save(user);
	}

	@Override
	public void createNewUser(User user) throws UsernameNotAvailable {
		String username = user.getUsername();
		if (!userRepository.findByUsername(username).isPresent()) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			LOG.debug(encodedPassword);
			rolesService.setAuthority(username, "USER");
			userRepository.save(user);
			LOG.info(String.format("Created new user with username: \"%s\"", username));
		} else {
			LOG.warn(String.format("Couldn't create new user! Reason: user \"%s\" already exists.", username));
			throw new UsernameNotAvailable(username);
		}
	}

	@Override
	public Iterable<User> allUsers() {
		return userRepository.findAll();
	}
}
