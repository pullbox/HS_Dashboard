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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Named;





@Named("welcomeBean")
@RequestScoped
public class WelcomeBean implements Serializable {

	private static final Log logger = LogFactory.getLog(WelcomeBean.class);
	private static final long serialVersionUID = 7778841766245989495L;
	
	@EJB
	private CallToActionService ctaService;
		
	private String username;
	private List<CallToAction> ctas;
	

	@PostConstruct
	public void init() {
		
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ctas = ctaService.list();
		
		
		/*ctas = null;
		ctas = new ArrayList<CallToAction>();
		
		DAOFactory factory = DAOFactory.getFactory();
		 ctadao = factory.getCallToActionDAO();
		 userdao = factory.getUSERDAO();
		
		 User user = new User();
		 user = userdao.findUserByUserID(getUserName());
		
		ctas =  ctadao.getAllCallToActions(user.getUSER_ID()); 
		*/	
		
		
	}

	@PreDestroy
	public void close() {
		
	}


	

	/**
	 * @return the ctas
	 */
	public List<CallToAction> getCtas() {
		return ctas;
	}

	/**
	 * @return the userid
	 */
	public String getUserName() {
		return username;
	}

}
