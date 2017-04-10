package net.bechtelus.junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bechtelus.common.DAOException;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.common.MSSQLDAOFactory;

public class TestMSSQLDAOFactory {

	public MSSQLDAOFactory msf = null;
	private Connection con = null;

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

	/*@Test
	public void testreadPropertyAndConnectionString() {
		try {
			assertEquals("mssql", DAOFactory.prop.getProperty("loalhost.HS_Dashboard.database.type"));
		} catch (Exception e) {
			fail("Cannot read db.properties " + e.toString());
		}

	}*/
}
