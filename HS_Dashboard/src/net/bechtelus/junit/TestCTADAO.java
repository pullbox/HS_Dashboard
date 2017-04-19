package net.bechtelus.junit;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;
import net.bechtelus.user.UserDAO;
import net.bechtelus.CTA.CallToAction;
import net.bechtelus.CTA.CallToActionDAO;

public class TestCTADAO {

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
	public void testMSSQLCTADAO() throws Exception {
		DAOFactory factory = DAOFactory.getFactory();

		/* Get the data access object */
		CallToActionDAO dao = factory.getCallToActionDAO();

		/* Create CTA Object */
		CallToAction cta = new CallToAction();
		cta = createCTAobject();

		boolean inserted = dao.insertCTA(cta);
		assertTrue("Record was inserted into the DB", inserted);

		/* test find function */
		CallToAction fcta = dao.getCallToActionById(1);
		assertEquals("Record was not found", "Test Call To Action", fcta.getDescription());

		/* Delete the record */
		dao.deleteCTA(1);

		/* test find function */
		fcta = dao.getCallToActionById(1);
		assertNull("Record was not found as it was deleted", fcta);

	}

	private CallToAction createCTAobject() {
		/* setup USER */
		DAOFactory factory = DAOFactory.getFactory();
		UserDAO userdao = factory.getUSERDAO();

		User assignee = new User();
		assignee = userdao.findUserByEmail("dbechtel@pentaho.com");

		/* setup snoozeperiod */
		DateTime snoozedt = new DateTime(2017, 9, 15, 9, 00);

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
		cta.setNote("this CTA was created as part of the JUNIT TEST");

		return cta;
	}

	@Test
	public void updateCTA() throws Exception {
		DAOFactory factory = DAOFactory.getFactory();

		/* Get the data access object */
		CallToActionDAO dao = factory.getCallToActionDAO();

		/* Create CTA Object */
		CallToAction cta = new CallToAction();
		cta = createCTAobject();
		
		boolean inserted = dao.insertCTA(cta);
		assertTrue("Record was inserted into the DB", inserted);

		/* test find function */
		cta = null;
		CallToAction fcta = dao.getCallToActionById(2);

		fcta.setDescription("Updated the Record");

		/* test find function */
		dao.updateCTA(fcta);

		/* find updated record and see if it was updated */
		fcta = null;
		cta = dao.getCallToActionById(2);
		assertEquals("Description Updated", "Updated the Record", cta.getDescription());
		assertNotNull(cta);

		cta = null;

		dao.deleteCTA(2);

	}

}
