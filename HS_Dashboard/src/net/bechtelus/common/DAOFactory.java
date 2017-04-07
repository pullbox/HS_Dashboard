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

	public static Properties prop = null;
	private static ConnectionProvider connectionProvider;

	public abstract CallToActionDAO getCustomerDAO() throws DAOException;

	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SYBASE = 3;
	public static final int MSSQL = 4;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	// public abstract StandardDAO getStandardDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MYSQL:
			// return new MySQLDAOFactory();
		case ORACLE:
			// return new OracleDAOFactory();
		case SYBASE:
			// return new SybaseDAOFactory();
		case MSSQL:
//			return new MSSQLDAOFactory();

		default:
			return null;
		}
	}
	
	public static void loadProperties() { 
        Log logger = LogFactory.getLog(DAOFactory.class); 
        if (prop == null) { 
            prop = new Properties(); 
//   MessageContext messageContext = MessageContext.getCurrentMessageContext(); 
//   if (messageContext != null) { 
//    AxisService service = messageContext.getAxisService(); 
//    ClassLoader serviceClassLoader = service.getClassLoader(); 
//    InputStream is = serviceClassLoader.getResourceAsStream(StockTraderUtility.DB_PROPERRTIES_FILE); 
//    if (is != null) { 
//     try { 
//      prop.load(is); 
//     } catch (IOException e) { 
//      logger.debug("Unable to load mysql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection",e); 
//     } 
//    } else { 
//     logger.debug("Unable to load mysql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection"); 
// 
//    } 
//   } 
 
            InputStream is = DAOFactory.class.getClassLoader().getResourceAsStream(HSDashboardUtility.DB_PROPERRTIES_FILE); 
            if (is != null) { 
                try { 
                    prop.load(is); 
                } catch (IOException e) { 
                    logger.debug("Unable to load mssql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection", e); 
                } 
            } else { 
                logger.debug("Unable to load mssql-db properties file and using [jdbc:mysql://localhost/stocktraderdb?user=trade&password=trade] as the default connection"); 
 
            } 
        } 
         
    } 
	

	public static ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}
}