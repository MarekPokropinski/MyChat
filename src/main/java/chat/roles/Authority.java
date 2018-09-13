package chat.roles;

import javax.persistence.Entity;
import javax.persistence.Table;

import chat.basemodel.BaseModel;

@Entity
@Table(name = "authorities")
public class Authority extends BaseModel {
	private String username;
	private String authority;

	public Authority(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
