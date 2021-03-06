package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardModel;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.security.LoginBean;
import net.bechtelus.successStories.SuccessStories;
import net.bechtelus.successStories.SuccessStoriesService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SuccessStoriesBean implements Serializable {

	

	
	private String userName;
	private SuccessStories css;

	@Inject
	private LoginBean loginBean;

	private static final Log logger = LogFactory.getLog(SuccessStoriesBean.class);
	private static final long serialVersionUID = 7778841766245989495L;

	@EJB
	private SuccessStoriesService cssService;

	private List<SuccessStories> csstories;
	private List<SuccessStories> filteredcss;
	
	
	public String doLogout() {
		loginBean.doLogout();
		 return "/login.xhtml?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		this.userName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
		logger.info("UserName: " + userName);
		
		populateCSStories();

	}

	@PreDestroy
	public void close() {
		cssService = null;
	}

	/**
	 * @return the csstories
	 */
	public void populateCSStories() {
		SuccessStoriesService cssService = new SuccessStoriesService();
		this.csstories = cssService.list();
		this.csstories.add(new SuccessStories("add new Success Story", false));
		cssService = null;
	}

	
	
	/**
	 * @return the filteredctas
	 */
	public List<SuccessStories> getFilteredcss() {
		return filteredcss;
	}

	/**
	 * @param filteredctas the filteredctas to set
	 */
	public void setFilteredriskctas(List<SuccessStories> filteredcss) {
		this.filteredcss = filteredcss;
	}

	/**
	 * @return the riskctas
	 */
	public List<SuccessStories> getCsstories() {
		return csstories;
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
