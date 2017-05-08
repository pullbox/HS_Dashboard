package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.security.LoginBean;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	private List<CallToAction> riskctas;
	private List<CallToAction> expansionctas;
	private List<CallToAction> adoptionctas;
	private List<CallToAction> lifecyclectas;
	private List<CallToAction> filteredctas;
	
	public String doLogout() {
		loginBean.doLogout();
		 return "/login.xhtml?faces-redirect=true";
	}

	public void init() {
		this.userName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
		logger.info("UserName: " + userName);
		populateCTAs();

	}

	@PreDestroy
	public void close() {
		ctaService = null;
	}

	/**
	 * @return the ctas
	 */
	public void populateCTAs() {
		ctaService = new CallToActionService();
		this.riskctas = ctaService.getCTAsByType("Risk");
		this.adoptionctas = ctaService.getCTAsByType("Adoption");
		this.expansionctas = ctaService.getCTAsByType("Expansion");
		this.lifecyclectas = ctaService.getCTAsByType("Lifecycle");
		ctaService = null;
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
	 * @return the filteredctas
	 */
	public List<CallToAction> getFilteredctas() {
		return filteredctas;
	}

	/**
	 * @param filteredctas the filteredctas to set
	 */
	public void setFilteredctas(List<CallToAction> filteredctas) {
		this.filteredctas = filteredctas;
	}

	/**
	 * @return the riskctas
	 */
	public List<CallToAction> getRiskctas() {
		return riskctas;
	}

	/**
	 * @return the expansionctas
	 */
	public List<CallToAction> getExpansionctas() {
		return expansionctas;
	}

	/**
	 * @return the adoptionctas
	 */
	public List<CallToAction> getAdoptionctas() {
		return adoptionctas;
	}

	/**
	 * @return the lifecyclectas
	 */
	public List<CallToAction> getLifecyclectas() {
		return lifecyclectas;
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
