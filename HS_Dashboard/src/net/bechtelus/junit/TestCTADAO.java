package net.bechtelus.junit;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

import javax.validation.constraints.AssertTrue;

import java.sql.SQLException;

import net.bechtelus.common.DAOException;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;
import net.bechtelus.CTA.CallToAction;
import net.bechtelus.CTA.CallToActionDAO;
import net.bechtelus.CTA.MSSQLCallToActionDAO;

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
		
		/* Delete the record  */
		dao.deleteCTA(1);
		
		/* test find function */
		fcta = dao.getCallToActionById(1);
		assertNull("Record was not found as it was deleted", fcta);
				
	}
	
	
	private CallToAction createCTAobject() {
		/* setup USER */
		DAOFactory factory = DAOFactory.getFactory();
		CallToActionDAO dao = factory.getCallToActionDAO();
		
		User assignee = new User();
		assignee = dao.getUserBySLFId("00550000002Ua4WAAS");
				

		/* setup snoozeperiod */
		DateTime snoozedt = new DateTime(2017, 9, 15, 9, 00);

		CallToAction cta = new CallToAction();
		cta.setId(1);
		cta.setDescription("Test Call To Action");
		cta.setAssignee(assignee);
		cta.setCtaType("RISK");
		cta.setStatus("NEW");
		cta.setPriority("HIGH");
		cta.setReason("Success Risk");
		cta.setSnoozeperiod(snoozedt);
		cta.setCtaStatus("OPEN");
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
		cta.setId(5);

		boolean inserted = dao.insertCTA(cta);
		assertTrue("Record was inserted into the DB", inserted);
		
		/* test find function */
		cta = null;
		CallToAction fcta = dao.getCallToActionById(5);
				
		fcta.setDescription("Updated the Record");
		
		/* test find function */
		dao.updateCTA(fcta);
			
		/* find updated record and see if it was updated  */
		fcta = null;
		cta = dao.getCallToActionById(5);		
		assertEquals("Description Updated", "Updated the Record", cta.getDescription());
		assertNotNull(cta);
		
		cta = null;
		
		dao.deleteCTA(5);
		
	}
	
	
	

	

}
