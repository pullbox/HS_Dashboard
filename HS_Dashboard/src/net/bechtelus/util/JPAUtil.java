package net.bechtelus.util;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAUtil {
	
	 public static void runExampleFor(String persistenceUnit, Class<?> entity) throws Exception{
	      EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
	      EntityManager em = emf.createEntityManager();

	      nativeQuery(em, "SELECT Distinct TABLE_NAME FROM information_schema.TABLES");
	      nativeQuery(em, "SELECT SEQUENCE_NAME  FROM INFORMATION_SCHEMA.SEQUENCES");
	      nativeQuery(em, "SELECT * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '"+ entity.getName()+"'");

	      Object entity1 = entity.newInstance();
	      Object entity2 = entity.newInstance();
	      em.getTransaction().begin();
	      em.persist(entity1);
	      em.persist(entity2);
	     em.getTransaction().commit();

	      nativeQuery(em, "SELECT * FROM "+entity.getSimpleName());

	      em.close();
	      emf.close();
	  }

	  private static void nativeQuery(EntityManager entityManager, String s) {
	      System.out.println("--------\n" + s);
	      Query query = entityManager.createNativeQuery(s);
	      List list = query.getResultList();
	      for (Object o : list) {
	          if (o instanceof Object[]) {
	              System.out.println(Arrays.toString((Object[]) o));
	          } else {
	              System.out.println(o);
	          }
	      }
	  }

}
