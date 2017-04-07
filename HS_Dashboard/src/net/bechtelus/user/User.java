package net.bechtelus.user;

public class User {
	private Long id;
	private String slf_user_id;
	private String username;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getSlf_user_id() {
		return slf_user_id;
	}

	
	public void setSlf_user_id(String slf_user_id) {
		this.slf_user_id = slf_user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}