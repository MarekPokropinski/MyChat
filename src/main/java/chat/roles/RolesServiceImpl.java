package chat.roles;

import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {
	private RolesRepository rolesRepository;

	public RolesServiceImpl(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	@Override
	public Authority setAuthority(String username, String authority) {
		return rolesRepository.save(new Authority(username, authority));
	}
}
