package net.bechtelus.security;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

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

	@PreDestroy
	public void close() {
		userService = null;
	}

	/**
	 * Login operation.
	 * 
	 * @return
	 */
	public String doLogin() {
		User user = new User();

		try {
			userService = new UserService();
			user = userService.findUserByEmail(getUsername());
		} catch (NoResultException e) {
			// Set login ERROR
			user = null;
			FacesMessage msg = new FacesMessage("No Account or Wrong Password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (Exception e) {
			// Set login ERROR
			user = null;
			FacesMessage msg = new FacesMessage("Login error!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {
			userService = null;
		}

		// Successful login
		if (user != null) {
			if (user.getEMAIL().equals(username)) {
				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", username);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", this);
				return navigationBean.redirectToWelcome();
			}
		}

	
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userName");
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

		return navigationBean.redirectToLogin();
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