package net.bechtelus.junit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bechtelus.common.DAOException;
import net.bechtelus.common.MSSQLDAOFactory;

public class TestMSSQLDAOFactory {

	private MSSQLDAOFactory msf;
	private Connection con;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		msf = new MSSQLDAOFactory();

	}

	@After
	public void tearDown() throws Exception {
		con.close();
		con = null;
	}

	@Test
	public void testCreateConnection() {
		try {
			con = MSSQLDAOFactory.createConnection();
		} catch (DAOException e) {
			fail("Connection failed! " + e.toString());
		}
		String name = con.getClass().getName();
		assertEquals("com.microsoft.sqlserver.jdbc.SQLServerConnection", name);

	}

}
