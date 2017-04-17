package net.bechtelus.junit;

import static org.junit.Assert.*;

import javax.persistence.*;
import net.bechtelus.user.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUser {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		EntityManagerFactory  ef = Persistence.createEntityManagerFactory("HS_Dashboard");
		EntityManager em = ef.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, "00550000000ribbAAA");
		assertNotNull(user);
		
		
	}

}
