package net.bechtelus.junit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.bechtelus.user.User;



public class FindUser {

	
	   public static void main( String[ ] args ) {
	   
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "HS_Dashboard" );
	      EntityManager entitymanager = emfactory.createEntityManager();
	      User u = entitymanager.find( User.class, "00550000000ribbAAA" );

	      System.out.println("employee ID = " + u.getUSER_ID());
	      System.out.println("employee NAME = " + u.getFULL_NAME());
	      System.out.println("employee SALARY = " + u.getTITLE());
	      
	   }
	}