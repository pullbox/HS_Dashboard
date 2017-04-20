package net.bechtelus.jsfbeans;

import java.io.Serializable;
import org.apache.commons.logging.LogFactory;

import com.sun.media.jfxmedia.logging.Logger;

import org.apache.commons.logging.Log;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("testView")
@ViewScoped
public class TestViewBean implements Serializable {

	private static final Log logger = LogFactory.getLog(TestViewBean.class);
	private static final long serialVersionUID = 7778841766245989495L;

	private String userName;
	private String aName;

	public void doLogout() {

	}

	public void init() {
		String aUsername = getUserName();
		Logger.logMsg(Logger.INFO, aUsername);

	}

	@PreDestroy
	public void close() {

	}

	/**
	 * @return the userid
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the userid
	 */
	public void setUserName(String aUname) {
		this.userName = aUname;
	}

	/**
	 * @return the aName
	 */
	public String getaName() {
		return aName;
	}

	/**
	 * @param aName the aName to set
	 */
	public void setaName(String aName) {
		this.aName = aName;
	}

	// This method is used to handle exceptions and display cause to user.
	public void handleException(RuntimeException ex) {
		StringBuffer details = new StringBuffer();
		Throwable causes = ex;
		while (causes.getCause() != null) {
			details.append(ex.getMessage());
			details.append("    Caused by:");
			details.append(causes.getCause().getMessage());
			causes = causes.getCause();
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
