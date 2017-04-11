package net.bechtelus.common;

import java.sql.*;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.CallToActionDAO;
import net.bechtelus.CTA.MSSQLCallToActionDAO;
import net.bechtelus.user.UserDAO;

public class MSSQLDAOFactory extends DAOFactory {
	public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=CS_ANALYTICS;integratedSecurity=true";

	private static Log logger = LogFactory.getLog(MSSQLDAOFactory.class);
	private static MSSQLDAOFactory self = null;
	private Connection con = null;
	private String connection = null;

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.warn("Unable to load " + DRIVER + " class", e);
		}
	}

	public static DAOFactory getInstance() {
		if (self == null) {
			self = new MSSQLDAOFactory();
		}
		return self;
	}

	private MSSQLDAOFactory() {
	}

	// method to create MSSQL connections
	public static Connection createConnection() {

		Connection con = null;
		try {
			// the sql server driver string

			con = DriverManager.getConnection(DBURL);

		} catch (SQLException e) {

			throw new DAOException("No Connection! Is the SQL Server started?", e);
		}

		finally {

		}

		return con;

	}

	private String getConnectionString() {
		if (connection == null) {
			loadProperties();
			if (prop.size() <= 0) {
				// if (prop == null) {
				connection = DBURL;
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("jdbc:sqlserver://");
				buf.append(prop.getProperty(PROP_DB_HOST));
				buf.append(":" + prop.getProperty(PROP_DB_PORT));
				buf.append(";DatabaseName=" + prop.getProperty(PROP_DB_NAME));
				// buf.append("; user=" + prop.getProperty(PROP_DB_USER));
				// buf.append("; password=" +
				// prop.getProperty(PROP_DB_PASSWORD));
				buf.append(";integratedSecurity=true");
				connection = buf.toString();
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MSSQLDAOFactory.getConnectionString()\nConnection :" + connection);
		}
		return connection;
	}

	private Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(getConnectionString());
		}
		return con;
	}

	@Override
	public CallToActionDAO getCallToActionDAO() throws DAOException {
		logger.debug("MSSQLDAOFactory.getCallToActionDAO");
		try {
			CallToActionDAO callToActionDAO = new MSSQLCallToActionDAO(getConnection());
			return callToActionDAO;
		} catch (SQLException e) {
			logger.debug("", e);
			throw new DAOException("Exception was thrown when instantiating a MSSQLCallToActionDAO", e);
		}
	}

}
