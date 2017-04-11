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
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/secured/welcome.xhtml?faces-redirect=true";
    }
     
    /**
     * Redirect to Overview page.
     * @return Welcome page name.
     */
    public String redirectToOverviewPage() {
        return "/secured/LandingPage.xhtml?faces-redirect=true";
    }
     
    /**
     * Redirect to create CTA page.
     * @return Welcome page name.
     */
    public String createCTA() {
        return "/secured/editcta.xhtml";
    }
     
    
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/welcome.xhtml";
    }
     
}