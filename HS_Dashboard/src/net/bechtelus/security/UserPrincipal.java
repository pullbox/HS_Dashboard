package net.bechtelus.security;

import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Serializable {

	private static final long serialVersionUID = 2341218887115456600L;
	private String name;

	public UserPrincipal(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String toString() {
        return("UserPrinciple.class:  " + name);
    }
	
	public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof UserPrincipal))
            return false;
        UserPrincipal that = (UserPrincipal)o;

        if (this.getName().equals(that.getName()))
            return true;
        return false;
    }
	
	 public int hashCode() {
	        return name.hashCode();
	    }
}