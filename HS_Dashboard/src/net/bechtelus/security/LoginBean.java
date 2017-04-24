package net.bechtelus.security;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.bechtelus.navigation.NavigationBean;
import net.bechtelus.user.User;
import net.bechtelus.user.UserService;
/**

 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 7765876811740798583L;


	private String password;

	private boolean loggedIn;
	private String username;
	
	@Inject
	private NavigationBean navigationBean;
	@EJB
	private UserService userService;

	@PostConstruct
	public void init() {

	}

	/**
	 * Login operation.
	 * 
	 * @return
	 */
	public String doLogin() {
		
				
		userService = new UserService();
		User user = userService.findUserByEmail(getUsername());

		// Successful login
		if (user != null) {
			if (user.getEMAIL().equals(username)) {
				loggedIn = true;
				return navigationBean.redirectToWelcome();
			}
		}

		// Set login ERROR
		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// To to login page
		/*
		 * ef = null; em = null;
		 */
		user = null;

		return navigationBean.toLogin();

	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		// Set the parameter indicating that user is logged in to false
		loggedIn = false;
		password = null;

		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	// ------------------------------
	// Getters & Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

}