package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.security.LoginBean;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@ViewScoped
// @URLMapping(id="welcomeBean", pattern="/welcome/#{welcomeBean.userName}",
// viewId = "/faces/secured/welcome.xhtml")
public class WelcomeBean implements Serializable {

	private String userName;

	@Inject
	private LoginBean loginBean;

	private static final Log logger = LogFactory.getLog(WelcomeBean.class);
	private static final long serialVersionUID = 7778841766245989495L;

	@EJB
	private CallToActionService ctaService;

	private List<CallToAction> ctas;
	private EntityManager em;

	public void doLogout() {
		loginBean.doLogout();
	}

	public void init() {
		String aUsername = getUserName();
		System.out.println("INIT:UserName: " + aUsername);
		this.ctas = getCtas();

	}

	@PreDestroy
	public void close() {
		ctaService = null;
	}

	/**
	 * @return the ctas
	 */
	public List<CallToAction> getCtas() {
		ctaService = new CallToActionService();
		this.ctas = ctaService.list();
		ctaService = null;
		return ctas;
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
