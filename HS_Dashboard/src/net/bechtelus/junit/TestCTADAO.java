package net.bechtelus.junit;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.AssertionFailedError;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;
import net.bechtelus.user.UserDAO;
import net.bechtelus.util.HSDashboardUtility;
import net.bechtelus.CTA.CallToAction;
import net.bechtelus.CTA.CallToActionDAO;

public class TestCTADAO {
	private EntityManager em;
	private long idOdon;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		em = HSDashboardUtility.getEMF().createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void wrtCTA() throws Exception {

		CallToAction cta = new CallToAction();
		cta = createCTAobject();

		try {
			em.getTransaction().begin();
			em.persist(cta);
			em.getTransaction().commit();

		} catch (Exception e) {
			fail(e.toString());
		} finally {

		}

	}

	@Test
	public void findUser() throws Exception {

		/* Create CTA Object */
		User user = new User();

		try {

			user = em.find(User.class, "00550000002Ua4WAAS");
			assertNotNull("failed retrieve user", user);

		} catch (Exception e) {
			fail(e.toString());
		} finally {

		}

	}

	/*
	 * @Test public void testMSSQLCTADAO() throws Exception { EntityManager em =
	 * HSDashboardUtility.getEMF().createEntityManager();
	 * 
	 * Create CTA Object CallToAction cta = new CallToAction(); cta =
	 * createCTAobject();
	 * 
	 * em.persist(cta); assertTrue("Record was inserted into the DB", inserted);
	 * 
	 * test find function CallToAction fcta = dao.getCallToActionById(1);
	 * assertEquals("Record was not found", "Test Call To Action",
	 * fcta.getDescription());
	 * 
	 * Delete the record dao.deleteCTA(1);
	 * 
	 * test find function fcta = dao.getCallToActionById(1);
	 * assertNull("Record was not found as it was deleted", fcta);
	 * 
	 * }
	 * 
	 */

	private CallToAction createCTAobject() {

		User assignee = new User();
		assignee = em.find(User.class, "00550000002Ua4WAAS");

		Date snoozedt = new Date();

		CallToAction cta = new CallToAction();
		cta.setDescription("Test Call To Action");
		cta.setAssignee(assignee);
		cta.setType("RISK");
		cta.setStatus("NEW");
		cta.setPriority("HIGH");
		cta.setReason("Success Risk");
		cta.setSnoozeperiod(snoozedt);
		cta.setStatus("OPEN");
		cta.setSource("TEST");
		cta.setCreateby(assignee);
		cta.setEscalated(true);
		cta.setDueDate(snoozedt);
		cta.setCreatedDate(new Date());
		cta.setNote("this CTA was created as part of the JUNIT TEST");

		return cta;
	}

	
}
