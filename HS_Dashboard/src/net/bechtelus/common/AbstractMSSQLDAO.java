package net.bechtelus.common;

 
import java.sql.Connection; 
import java.sql.SQLException; 
 
 
 
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
 
public class AbstractMSSQLDAO { 
 
 private static final Log logger = LogFactory.getLog(AbstractMSSQLDAO.class); 
 
 protected Connection sqlConnection; 
 
 private int previousTransactionIsolation; 
 
 public AbstractMSSQLDAO(Connection sqlConnection) throws DAOException { 
  this.sqlConnection = sqlConnection; 
 } 
 
 public void beginTransaction() throws DAOException { 
  logger.debug("AbstractMSSQLDAO.beginTransaction()"); 
  try { 
   sqlConnection.setAutoCommit(false); 
   previousTransactionIsolation = sqlConnection.getTransactionIsolation(); 
   sqlConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); 
  } catch (SQLException e) { 
   e.printStackTrace(); 
   logger.debug("", e); 
   throw new DAOException("Exception was thrown during the start of transaction", e); 
  } 
 } 
 
 public void commitTransaction() throws DAOException { 
  logger.debug("AbstractMSSQLDAO.commitTransaction()"); 
  try { 
   sqlConnection.commit(); 
   sqlConnection.setAutoCommit(true); 
   sqlConnection.setTransactionIsolation(previousTransactionIsolation); 
  } catch (SQLException e) { 
   throw new DAOException("Exception is thrown during the commit of transaction", e); 
  } 
 } 
 
 public void rollbackTransaction() throws DAOException { 
  logger.debug("AbstractMySQLDAO.rollbackTransaction()"); 
  try { 
   sqlConnection.rollback(); 
   sqlConnection.setAutoCommit(true); 
   sqlConnection.setTransactionIsolation(previousTransactionIsolation); 
  } catch (SQLException e) { 
   throw new DAOException("Exception is thrown during the rollback of transaction", e); 
 
  } 
 } 
 
 public void close() throws DAOException { 
  try { 
   sqlConnection.close(); 
  } catch (SQLException e) { 
   throw new DAOException("", e); 
  } 
 } 
}