package net.bechtelus.user;

public class User {
	protected Long id;
	protected String slf_user_id;
	protected String username;
	protected String password;
	protected String email;

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
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public User(Long aID, String aSLF_user_id, String aName, String aPassword, String aemail) {
		setId(aID);
		setSlf_user_id(aSLF_user_id);
		setUsername(aName);
		setPassword(aPassword);
		setEmail(aemail);
	}
	
	public User() {
		
	}
	
}