package chat.user;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chat.chatRoom.ChatRoom;
import chat.chatRoom.ChatRoomService;
import chat.chatRoom.RoomException;
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

		try {
			createNewUser(new User("admin", "admin", "place@holder.com"));
			rolesService.setAuthority("admin", "ADMIN");
			LOG.info("created new admin account");
		} catch (UsernameNotAvailable e) {
			LOG.info("admin user exists");
		}
		try {
			User admin = getUserByUsername("admin").get();
			chatRoomService.createChatRoom("public", admin);
		} catch (RoomException e) {
			LOG.info("public room exists");
		}
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void addUserToChatRoom(User user, String chatRoomName) throws RoomException {
		var chatRooms = user.getChatRooms();
		ChatRoom chatRoom = chatRoomService.getChatRoomByName(chatRoomName)
				.orElseThrow(() -> RoomException.roomNotFound);

		chatRooms.add(chatRoom);
		userRepository.save(user);
	}

	@Override
	public User createNewUser(User user) throws UsernameNotAvailable {
		String username = user.getUsername();
		if (!userRepository.findByUsername(username).isPresent()) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			LOG.debug(encodedPassword);
			rolesService.setAuthority(username, "USER");
			User createdUser = userRepository.save(user);
			try {
				addUserToChatRoom(createdUser, "public");
			} catch (RoomException e) {
				LOG.error("public channel does not exist");
			}
			LOG.info(String.format("Created new user with username: \"%s\"", username));
			return createdUser;
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
