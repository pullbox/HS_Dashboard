package net.bechtelus.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bechtelus.common.DAOFactory;

@Entity
@Table(name="sf_users")
public class User {
	
	private Long USER_TK;
	
	@Id
	private String USER_ID;
	private String FULL_NAME;
	private Boolean ACTIVE;
	private String EMAIL;
	private String TITLE;

	

	/**
	 * @return the uSER_TK
	 */
	public Long getUSER_TK() {
		return USER_TK;
	}


	/**
	 * @param uSER_TK the uSER_TK to set
	 */
	public void setUSER_TK(Long uSER_TK) {
		USER_TK = uSER_TK;
	}


	/**
	 * @return the uSER_ID
	 */
	public String getUSER_ID() {
		return USER_ID;
	}


	/**
	 * @param uSER_ID the uSER_ID to set
	 */
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}


	/**
	 * @return the fULL_NAME
	 */
	public String getFULL_NAME() {
		return FULL_NAME;
	}


	/**
	 * @param fULL_NAME the fULL_NAME to set
	 */
	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}


	/**
	 * @return the aCTIVE
	 */
	public Boolean getACTIVE() {
		return ACTIVE;
	}


	/**
	 * @param aCTIVE the aCTIVE to set
	 */
	public void setACTIVE(Boolean aCTIVE) {
		ACTIVE = aCTIVE;
	}


	/**
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}


	/**
	 * @param eMAIL the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}


	/**
	 * @return the tITLE
	 */
	public String getTITLE() {
		return TITLE;
	}


	/**
	 * @param tITLE the tITLE to set
	 */
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}


	public User(Long user_tk, String user_id, String full_name, Boolean active, String email, String title) {
		this.setACTIVE(active);
		this.setEMAIL(email);
		this.setFULL_NAME(full_name);
		this.setTITLE(title);
		this.setUSER_ID(user_id);
		this.setUSER_TK(user_tk);
	}
	
		
	public User() {
		
	}
	
}