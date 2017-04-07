package net.bechtelus.common;

public class UserAuthenticationFactory {
	
	public static UserAuthentication getUserAuthentication(String type){
		 
		if (type.equalsIgnoreCase("jdbc")){
			return new JDBCUserAuthentication();
		}else {
			return new JDBCUserAuthentication();
		}
	}

}
