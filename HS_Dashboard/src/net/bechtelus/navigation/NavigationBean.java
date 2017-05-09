package net.bechtelus.navigation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named("navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
 
   private String userName; 
	 
    private static final long serialVersionUID = 1520318172495977648L;
 
    
    
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
     
   
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
    	return "/secured/welcome.xhtml?faces-redirect=true";
    }
     
   
    /*public String redirectToWelcome() {
        return "/secured/welcome.xhtml?faces-redirect=true&includeViewParams=true";
    }
    */ 
     
    /**
     * Redirect to create CTA page.
     * @return Welcome page name.
     */
    public String createCTA() {
        return "/secured/editCTA.xhtml?faces-redirect=true&includeViewParams=true";
    }
     
    
    public String editCTA() {
    	String ctaID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ctaid");
    	System.out.println("CTAID: " + ctaID);
        return "/secured/editCTA.xhtml?faces-redirect=true&ctaid=" +ctaID;
    }
     
    
    public void deferCTA() {
    	String ctaID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ctaid");
    	String userName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
    	System.out.println("CTAID: " + ctaID);
    	
    	 Map<String,Object> options = new HashMap<String, Object>();
    	 Map<String,List<String>> params = new HashMap<String, List<String>>();
    	 
         options.put("modal", true);
         options.put("width", 640);
         options.put("height", 340);
         options.put("contentWidth", "100%");
         options.put("contentHeight", "100%");
         options.put("headerElement", "customheader");
         
         List<String> ids = new Vector<String>();
         ids.add(ctaID);
         List<String> uname = new Vector<String>();
         uname.add(userName);
         params.put("ctaid", ids);
         params.put("username", uname);
          
         RequestContext.getCurrentInstance().openDialog("deferCTA", options, params);
    }
   
     
}