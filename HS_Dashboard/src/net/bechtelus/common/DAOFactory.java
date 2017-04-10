package net.bechtelus.common;

//import net.bechtelus.standard.StandardDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.bechtelus.CTA.CallToActionDAO;
import net.bechtelus.util.HSDashboardUtility;

//Abstract class DAO Factory
public abstract class DAOFactory {
	public static final String PROP_DB_HOST = "loalhost.HS_Dashboard.database.host";
	public static final String PROP_DB_PORT = "loalhost.HS_Dashboard.database.port";
	public static final String PROP_DB_NAME = "loalhost.HS_Dashboard.database.db";
	public static final String PROP_DB_USER = "loalhost.HS_Dashboard.database.user";
	public static final String PROP_DB_PASSWORD = "loalhost.HS_Dashboard.database.password";
	public static final String PROP_DB_TYPE = "loalhost.HS_Dashboard.database.type";

	public static Properties prop = null;
	private static ConnectionProvider connectionProvider;

	public abstract CallToActionDAO getCallToActionDAO() throws DAOException;

	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SYBASE = 3;
	public static final int MSSQL = 4;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	// public abstract StandardDAO getStandardDAO();

	/*
	 * public static DAOFactory getDAOFactory(int whichFactory) {
	 * 
	 * switch (whichFactory) { case MYSQL: // return new MySQLDAOFactory(); case
	 * ORACLE: // return new OracleDAOFactory(); case SYBASE: // return new
	 * SybaseDAOFactory(); case MSSQL: // return new MSSQLDAOFactory();
	 * 
	 * default: return null; } }
	 */

	public static DAOFactory getFactory() {
		DAOFactory factory;
		loadProperties();
		if ("mysql".equals(prop.getProperty(PROP_DB_TYPE))) {
			 System.out.println("MYSQL: DISABLED FOR NOW"); 
			throw new IllegalArgumentException("Disabling MYSQL");
		} else if ("mssql".equals(prop.getProperty(PROP_DB_TYPE))) {
			 factory = MSSQLDAOFactory.getInstance();
			 return factory;
		} else {
			throw new IllegalArgumentException("Unknown Database type " + prop.getProperty(PROP_DB_TYPE));
		}
	}

	public DAOFactory() {
		loadProperties();
	}

	public static void loadProperties() {
		Log logger = LogFactory.getLog(DAOFactory.class);
		if (prop == null) {
			prop = new Properties();
			// MessageContext messageContext =
			// MessageContext.getCurrentMessageContext();
			// if (messageContext != null) {
			// AxisService service = messageContext.getAxisService();
			// ClassLoader serviceClassLoader = service.getClassLoader();
			// InputStream is =
			// serviceClassLoader.getResourceAsStream(StockTraderUtility.DB_PROPERRTIES_FILE);
			// if (is != null) {
			// try {
			// prop.load(is);
			// } catch (IOException e) {
			// logger.debug("Unable to load mysql-db properties file and using
			// [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade]
			// as the default connection",e);
			// }
			// } else {
			// logger.debug("Unable to load mysql-db properties file and using
			// [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade]
			// as the default connection");
			//
			// }
			// }

			InputStream is = DAOFactory.class.getClassLoader().getResourceAsStream(HSDashboardUtility.DB_PROPERRTIES_FILE);
			if (is != null) {
				try {
					prop.load(is);
				} catch (IOException e) {
					logger.debug(
							"Unable to load mssql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection",
							e);
				}
			} else {
				logger.debug(
						"Unable to load mssql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection");

			}
		}

	}

	public static ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

}