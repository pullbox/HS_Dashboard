package net.bechtelus.navigation;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {
 
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
        return "/secured/welcome.xhtml?faces-redirect=true&includeViewParams=true";
    }
     
   
     
    /**
     * Redirect to create CTA page.
     * @return Welcome page name.
     */
    public String createCTA() {
        return "/secured/editCTA.xhtml?faces-redirect=true&includeViewParams=true";
    }
     
    
   
     
}