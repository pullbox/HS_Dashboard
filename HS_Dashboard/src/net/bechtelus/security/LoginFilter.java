// http://www.itcuties.com/j2ee/jsf-2-login-filter-example/

package net.bechtelus.security;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bechtelus.security.LoginBean;

public class LoginFilter implements Filter {

	
	 private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		        + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURL = request.getContextPath() + "/faces/login.xhtml";

		// Get the loginBean from session attribute
		LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");

		boolean loggedIn = (session != null) && (loginBean != null) && (loginBean.isLoggedIn());

		boolean loginRequest = request.getRequestURI().equals(loginURL);
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
/*
		// For the first application request there is no loginBean in the
		// session so user needs to log in
		// For other requests loginBean is present but we need to check if user
		// has logged in successfully

		if (loginBean != null) {
			System.out.println("LoginBean: " + loginBean.toString());
			System.out.println("logged in: " + loginBean.isLoggedIn());
		}
		if (loginBean == null || !loginBean.isLoggedIn()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login.xhtml");
		}

		chain.doFilter(request, response);*/
		
		
		 if (loggedIn || loginRequest || resourceRequest) {
	            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also http://stackoverflow.com/q/4194207/157882
	                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	                response.setDateHeader("Expires", 0); // Proxies.
	            }

	            chain.doFilter(request, response); // So, just continue request.
	        }
	        else if (ajaxRequest) {
	            response.setContentType("text/xml");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
	        }
	        else {
	            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
	        }
		
		

	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("loginfilter initalized");
	}

	public void destroy() {
		// Nothing to do here!
	}

}