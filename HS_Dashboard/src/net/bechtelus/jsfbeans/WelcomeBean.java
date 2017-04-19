package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;
import net.bechtelus.user.UserDAO;
import net.bechtelus.util.HSDashboardUtility;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;





@Named("welcomeBean")
@ViewScoped
public class WelcomeBean implements Serializable {

	private static final Log logger = LogFactory.getLog(WelcomeBean.class);
	private static final long serialVersionUID = 7778841766245989495L;
	
	@EJB
	private CallToActionService ctaService;


	private String username;
	private List<CallToAction> ctas;
	private EntityManager em;


	@PostConstruct
	public void init() {
			
		
	}

	@PreDestroy
	public void close() {
		
	}


	

	/**
	 * @return the ctas
	 */
	public List<CallToAction> getCtas() {
		ctas = ctaService.list();		
		return ctas;
	}

	/**
	 * @return the userid
	 */
	public String getUserName() {
		return username;
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
