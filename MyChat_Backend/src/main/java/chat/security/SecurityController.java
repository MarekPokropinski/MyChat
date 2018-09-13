package chat.security;

import java.security.Principal;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	private static final Logger LOG = Logger.getLogger(SecurityController.class);

	@RequestMapping("/user")
	public Principal user(Principal user) {
		LOG.info(String.format("User logged in as \"%s\"", user.getName()));
		return user;
	}
}
