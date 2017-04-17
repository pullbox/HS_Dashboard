package net.bechtelus.CTA;

import static net.bechtelus.common.DAOUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import org.joda.time.*;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import net.bechtelus.common.AbstractMSSQLDAO;
import net.bechtelus.common.DAOException;
import net.bechtelus.common.DAOFactory;

import net.bechtelus.user.MSSQLUserDAO;
import net.bechtelus.user.User;
import net.bechtelus.user.UserDAO;
import net.bechtelus.util.HSDashboardUtility;

@Stateless
public class MSSQLCallToActionDAO extends AbstractMSSQLDAO implements CallToActionDAO {
	
	
	@PersistenceContext
	private static final String SQL_SELECT_CALLTOACTION_BY_ID = "SELECT * from dbo.CS_HS_CallToActions WHERE id = ?";
	private static final String SQL_SELECT_ALL_CTAS = "SELECT * from dbo.CS_HS_CallToActions WHERE ASSIGNEE = ?";
	
	private static final String SQL_UPDATE_CALLTOACTION = "Update dbo.CS_HS_CallToActions SET DESCRIPTION=?, ASSIGNEE=?, TYPE=?, STATUS=?, PRIORITY=?, REASON=?, SNOOZEPERIOD=?, CTASTATUS=?, SOURCE=?, MODIFIEDBY=?, ESCALATED=?, DUEDATE=?, NOTE=?, SNOOZEREASON=?, MODIFIEDDATE=getdate() WHERE ID = ?";
	private static final String SQL_DELETE_CALLTOACTION = "DELETE FROM dbo.CS_HS_CallToActions WHERE id = ";
	
	private static final String SQL_INSERT_CALLTOACTION = "INSERT INTO dbo.CS_HS_CallToActions (ID, DESCRIPTION, ASSIGNEE, TYPE, STATUS, PRIORITY, REASON, SNOOZEPERIOD, CTASTATUS, SOURCE, CREATEDBY, ESCALATED, DUEDATE, NOTE, SNOOZEREASON, CREATEDDATE) VALUES (NEXT VALUE FOR seqCTA, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getdate()); SELECT SCOPE_IDENTITY();";
	private static final String SQL_SELECT_CALLTOACTIONS_BY_ID = " cta.id, cta.DESCRIPTION, cta.ASSIGNEE, cta.TYPE, cta.STATUS, cta.PRIORITY, cta.REASON, cta.SNOOZEPERIOD, cta.CTASTATUS, cta.SOURCE, cta.MODIFIEDBY, cta.ESCALATED, cta.DUEDATE, cta.NOTE, cta.SNOOZEREASON, cta.MODIFIEDDATE FROM dbo.CS_HS_CallToActions cta WHERE cta.ASSIGNEE = ? ORDER BY ID DESC";

	private static final Log logger = LogFactory.getLog(MSSQLCallToActionDAO.class);

	public MSSQLCallToActionDAO(Connection sqlConnection) throws DAOException {
		super(sqlConnection);
	}

	/*
	 * public List<CallToAction> getCallToActions(String userId, boolean top,
	 * int maxTop, int maxDefault) throws DAOException { PreparedStatement
	 * selectCTAsById = null; try { String sqlQuery; if (top) { sqlQuery =
	 * "SELECT " + SQL_SELECT_ORDERS_BY_ID + " LIMIT " + maxTop; } else {
	 * sqlQuery = "SELECT " + SQL_SELECT_ORDERS_BY_ID + " LIMIT " + maxDefault;
	 * } selectOrdersById = sqlConnection.prepareStatement(sqlQuery);
	 * selectOrdersById.setString(1, userId); ResultSet rs =
	 * selectOrdersById.executeQuery(); List<Order> orders = new
	 * ArrayList<Order>();
	 * 
	 * try { while (rs.next()) { int orderId = rs.getInt(1); Calendar openDate =
	 * StockTraderUtility.convertToCalendar(rs.getDate(4)); Calendar
	 * completionDate = null; try { if (rs.getDate(5) != null) { completionDate
	 * = StockTraderUtility.convertToCalendar(rs.getDate(5)); } else {
	 * completionDate = Calendar.getInstance();
	 * completionDate.setTimeInMillis(0); } } catch (SQLException e) {
	 * logger.debug("", e); completionDate = Calendar.getInstance();
	 * completionDate.setTimeInMillis(0); }
	 * 
	 * Order orderBean = new Order(orderId, rs.getString(2), rs.getString(3),
	 * openDate, completionDate, rs.getDouble(6), rs.getBigDecimal(7),
	 * rs.getBigDecimal(8), rs.getString(9)); orders.add(orderBean); }
	 * 
	 * } finally { try { rs.close(); } catch (SQLException e) { logger.debug("",
	 * e); } } return orders;
	 * 
	 * } catch (SQLException e) { throw new DAOException("", e); } finally { if
	 * (selectOrdersById != null) { try { selectOrdersById.close(); } catch
	 * (SQLException e) { logger.debug("", e); } } } }
	 */
	/*
	 * @Override public List<CallToAction> listCallToActions() { Connection con
	 * = null; Statement stmt = null; ResultSet rs = null; List<CallToAction>
	 * ctas = new ArrayList<CallToAction>();
	 * 
	 * try { con = mssqlFactory.createConnection(); stmt =
	 * con.createStatement(); rs = stmt.executeQuery(SQL_LIST_ORDER_BY_ORDER);
	 * if (rs == null) { throw new DAOException("No Data"); }
	 * 
	 * try { while (rs.next()) { CallToAction cta = new CallToAction();
	 * cta.setId(rs.getLong("id"));
	 * cta.setApplicable(rs.getBoolean("always_applicable"));
	 * cta.setDescription(rs.getString("description"));
	 * cta.setName(rs.getString("name")); cta.setOrder(rs.getInt("sort_order"));
	 * cta.setType(rs.getString("type")); cta.setWeight(rs.getInt("weight"));
	 * 
	 * ctas.add(cta); } } catch (Exception e) { throw new
	 * DAOException("ARGHh Something went wrong! MSSQLUserDAO", e); }
	 * 
	 * } catch (SQLException e) { throw new DAOException(e); } finally {
	 * close(con, stmt, rs); } return ctas; }
	 */

	/*
	 * @Override public void update(CallToAction standard) throws
	 * IllegalArgumentException, DAOException { if (cta.getId() == 0) { throw
	 * new IllegalArgumentException("No ID! Something went terribly wrong!"); }
	 * 
	 * Object[] values = { cta.getName(), cta.getType(), cta.getDescription(),
	 * cta.getWeight(), cta.getOrder(), cta.isApplicable() };
	 * 
	 * Connection con = null; PreparedStatement prpstmt = null; ResultSet
	 * generatedkeys = null;
	 * 
	 * try { con = MySQLDAOFactory.createConnection(); prpstmt =
	 * prepareStatement(con, SQL_UPDATE, true, values);
	 * 
	 * int effectedrows = prpstmt.executeUpdate();
	 * 
	 * if (effectedrows == 0) { throw new
	 * DAOException("Update failed as no rows were effected."); }
	 * 
	 * } catch (SQLException e) { throw new DAOException(e); } finally {
	 * close(con, prpstmt, generatedkeys); } }
	 */

	/*
	 * @Override public void delete(int id) { if (id == 0) { throw new
	 * IllegalArgumentException("Key is not set. ID is 0."); }
	 * 
	 * Object[] values = { id };
	 * 
	 * Connection con = null; PreparedStatement prpstmt = null; ResultSet
	 * generatedkeys = null;
	 * 
	 * try { con = MySQLDAOFactory.createConnection(); prpstmt =
	 * prepareStatement(con, SQL_DELETE, true, values);
	 * 
	 * int effectedrows = prpstmt.executeUpdate();
	 * 
	 * if (effectedrows == 0) { throw new
	 * DAOException("Deleting CallToAction failed, no rows affected."); }
	 * 
	 * } catch (SQLException e) { throw new DAOException(e); } finally {
	 * close(con, prpstmt, generatedkeys); }
	 * 
	 * }
	 */

	/*
	 * @Override public CallToAction findbyKey(long aid) { Connection con =
	 * null; PreparedStatement prpstmt = null; ResultSet rs = null; CallToAction
	 * cta = null; try { con = MySQLDAOFactory.createConnection(); prpstmt =
	 * prepareStatement(con, SQL_FIND_BY_ID, true, aid);
	 * 
	 * rs = prpstmt.executeQuery();
	 * 
	 * if (rs == null) { throw new DAOException("No Records fount!"); }
	 * 
	 * try { while (rs.next()) { cta = new CallToAction();
	 * cta.setId(rs.getLong("id"));
	 * cta.setApplicable(rs.getBoolean("always_applicable"));
	 * cta.setDescription(rs.getString("description"));
	 * cta.setName(rs.getString("name")); cta.setOrder(rs.getInt("sort_order"));
	 * cta.setType(rs.getString("type")); cta.setWeight(rs.getInt("weight"));
	 * 
	 * } } catch (Exception e) { throw new
	 * DAOException("ARGHh Something went wrong! MSSQLUserDAO", e); }
	 * 
	 * } catch (SQLException e) { throw new DAOException(e); } finally {
	 * close(con, prpstmt, rs); } return cta;
	 * 
	 * }
	 */

	@Override
	public boolean insertCTA(CallToAction cta) throws DAOException {

		PreparedStatement insertCTA = null;
		boolean insertSuccess = false;
		try {

			insertCTA = sqlConnection.prepareStatement(SQL_INSERT_CALLTOACTION);
			//insertCTA.setLong(1, cta.getId());
			insertCTA.setString(1, cta.getDescription());
			insertCTA.setString(2, cta.getAssignee().getUSER_ID());
			insertCTA.setString(3, cta.getCtaType());
			insertCTA.setString(4, cta.getStatus());
			insertCTA.setString(5, cta.getPriority());
			insertCTA.setString(6, cta.getReason());
			insertCTA.setDate(7, new java.sql.Date(cta.getSnoozeperiod().getMillis()));
			insertCTA.setString(8, cta.getCtaStatus());
			insertCTA.setString(9, cta.getSource());
			insertCTA.setString(10, cta.getCreateby().getUSER_ID());
			insertCTA.setBoolean(11, cta.isEscalated());
			insertCTA.setDate(12, new java.sql.Date(cta.getDueDate().getMillis()));
			insertCTA.setString(13, cta.getNote());
			insertCTA.setString(14, cta.getSnoozeReason());
			insertCTA.executeUpdate();
			insertSuccess = true;

		} catch (SQLException e) {
			insertSuccess = false;
			throw new DAOException("Insert CTA failed", e);

		} finally {
			if (insertCTA != null) {
				try {
					insertCTA.close();
				} catch (SQLException e) {
					logger.debug("InsertCTA failed", e);
				}
			}
		}
		return insertSuccess;

	}

	@Override
	public void updateCTA(CallToAction cta) throws DAOException {
		if (logger.isDebugEnabled()) {
			logger.debug("MSSQLUserDAO.updateCTA(int)\n ID :" + Long.toString(cta.getId()));
		}

		PreparedStatement updateCallToAction = null;
		try {
			updateCallToAction = sqlConnection.prepareStatement(SQL_UPDATE_CALLTOACTION);

			updateCallToAction.setString(1, cta.getDescription());
			updateCallToAction.setNull(2, java.sql.Types.VARCHAR);
			if (cta.getAssignee() != null) {
				updateCallToAction.setString(2, cta.getAssignee().getUSER_ID());
			}
			updateCallToAction.setString(3, cta.getCtaType());
			updateCallToAction.setString(4, cta.getStatus());
			updateCallToAction.setString(5, cta.getPriority());
			updateCallToAction.setString(6, cta.getReason());

			if (cta.getSnoozeperiod() != null) {
				updateCallToAction.setDate(7, new java.sql.Date(cta.getSnoozeperiod().getMillis()));
			} else {
				updateCallToAction.setNull(7, java.sql.Types.DATE);
			}
			updateCallToAction.setString(8, cta.getCtaStatus());
			updateCallToAction.setString(9, cta.getSource());

			if (cta.getModifiedby() != null) {
				updateCallToAction.setString(10, cta.getModifiedby().getUSER_ID());
			} else {
				updateCallToAction.setNull(10, java.sql.Types.VARCHAR);
			}
			updateCallToAction.setBoolean(11, cta.isEscalated());

			if (cta.getDueDate() != null) {
				updateCallToAction.setDate(12, new java.sql.Date(cta.getDueDate().getMillis()));
			} else {
				updateCallToAction.setNull(12, java.sql.Types.DATE);
			}
			updateCallToAction.setString(13, cta.getNote());
			updateCallToAction.setString(14, cta.getSnoozeReason());
			updateCallToAction.setLong(15, cta.getId());

			updateCallToAction.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("", e);
		} finally {
			if (updateCallToAction != null) {
				try {
					updateCallToAction.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
	}

	@Override
	public void deleteCTA(long id) throws DAOException {
		Statement deleteCTA = null;
		try {
			deleteCTA = sqlConnection.createStatement();

			String sql = SQL_DELETE_CALLTOACTION + Long.toString(id);
			deleteCTA.executeUpdate(sql);
		} catch (SQLException e) {
			logger.debug("", e);
		}
	}

	@Override
	public CallToAction getCallToActionById(long id) throws DAOException {

		PreparedStatement callToActionbyId = null;
		try {
			callToActionbyId = sqlConnection.prepareStatement(SQL_SELECT_CALLTOACTION_BY_ID);
			callToActionbyId.setLong(1, id);
			ResultSet rs = callToActionbyId.executeQuery();

			if (rs.next()) {
				try {
					CallToAction cta = new CallToAction();

					cta.setId(rs.getLong("ID"));
					cta.setAssignee(getUserBySLFId(rs.getString("ASSIGNEE")));
					cta.setCreateby(getUserBySLFId(rs.getString("CREATEDBY")));
					cta.setCreatedDate(new DateTime(rs.getDate("CREATEDDATE")));
					cta.setCtaStatus(rs.getString("CTASTATUS"));
					cta.setCtaType(rs.getString("TYPE"));
					cta.setDescription(rs.getString("DESCRIPTION"));
					cta.setDueDate(new DateTime(rs.getDate("DUEDATE")));
					cta.setEscalated(rs.getBoolean("ESCALATED"));
					cta.setNote(rs.getString("NOTE"));
					cta.setPriority(rs.getString("PRIORITY"));
					cta.setReason(rs.getString("REASON"));
					cta.setSnoozeperiod(new DateTime(rs.getDate("SNOOZEPERIOD")));
					cta.setSnoozeReason(rs.getString("SNOOZEREASON"));
					cta.setSource(rs.getString("SOURCE"));
					cta.setStatus(rs.getString("STATUS"));
					return cta;
				} finally {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.debug("", e);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("", e);
		} finally {
			if (callToActionbyId != null) {
				try {
					callToActionbyId.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
		return null;
	}

	public User getUserBySLFId(String aUser_ID) throws DAOException {
		
		DAOFactory factory = DAOFactory.getFactory();
		UserDAO dao = factory.getUSERDAO();
		
		User user = new User();
		user = dao.findUserByUserID(aUser_ID);
		
		return user;
	}

	@Override
	public List<CallToAction> getCallToActions(String userId, boolean top, int maxTop, int maxDefault)
			throws DAOException {
		 PreparedStatement getCTAs = null; 
	        try { 
	            String sqlQuery; 
	            if (top) { 
	                sqlQuery = "SELECT " + SQL_SELECT_CALLTOACTIONS_BY_ID + " LIMIT " + maxTop; 
	            } else { 
	                sqlQuery = "SELECT " + SQL_SELECT_CALLTOACTIONS_BY_ID + " LIMIT " + maxDefault; 
	            } 
	            getCTAs = sqlConnection.prepareStatement(sqlQuery); 
	            getCTAs.setString(1, userId); 
	            ResultSet rs = getCTAs.executeQuery(); 
	            List<CallToAction> ctas = new ArrayList<CallToAction>(); 
	 
	            try { 
	                while (rs.next()) { 
	                    
	                	CallToAction cta = new CallToAction();

						cta.setId(rs.getLong("ID"));
						cta.setAssignee(getUserBySLFId(rs.getString("ASSIGNEE")));
						cta.setCreateby(getUserBySLFId(rs.getString("CREATEDBY")));
						cta.setCreatedDate(new DateTime(rs.getDate("CREATEDDATE")));
						cta.setCtaStatus(rs.getString("CTASTATUS"));
						cta.setCtaType(rs.getString("TYPE"));
						cta.setDescription(rs.getString("DESCRIPTION"));
						cta.setDueDate(new DateTime(rs.getDate("DUEDATE")));
						cta.setEscalated(rs.getBoolean("ESCALATED"));
						cta.setNote(rs.getString("NOTE"));
						cta.setPriority(rs.getString("PRIORITY"));
						cta.setReason(rs.getString("REASON"));
						cta.setSnoozeperiod(new DateTime(rs.getDate("SNOOZEPERIOD")));
						cta.setSnoozeReason(rs.getString("SNOOZEREASON"));
						cta.setSource(rs.getString("SOURCE"));
						cta.setStatus(rs.getString("STATUS"));
						ctas.add(cta);
	                } 
	 
	            } finally { 
	                try { 
	                    rs.close(); 
	                } catch (SQLException e) { 
	                    logger.debug("", e); 
	                } 
	            } 
	            return ctas; 
	 
	        } catch (SQLException e) { 
	            throw new DAOException("", e); 
	        } finally { 
	            if (getCTAs != null) { 
	                try { 
	                	getCTAs.close(); 
	                } catch (SQLException e) { 
	                    logger.debug("", e); 
	                } 
	            } 
	        } 
	    } 
	 
	
@Override
	public List<CallToAction> getAllCallToActions(String userId) throws DAOException {
		PreparedStatement getAllCTAs = null;
		// PreparedStatement updateClosedOrders = null;
		try {
			getAllCTAs = sqlConnection.prepareStatement(SQL_SELECT_ALL_CTAS);
			getAllCTAs.setString(1, userId);
			ResultSet rs = getAllCTAs.executeQuery();
			List<CallToAction> ctas = new ArrayList<CallToAction>();

			try {
				while (rs.next()) {

					CallToAction cta = new CallToAction();

					cta.setId(rs.getLong("ID"));
					cta.setAssignee(getUserBySLFId(rs.getString("ASSIGNEE")));
					cta.setCreateby(getUserBySLFId(rs.getString("CREATEDBY")));
					cta.setCreatedDate(new DateTime(rs.getDate("CREATEDDATE")));
					cta.setCtaStatus(rs.getString("CTASTATUS"));
					cta.setCtaType(rs.getString("TYPE"));
					cta.setDescription(rs.getString("DESCRIPTION"));
					cta.setDueDate(new DateTime(rs.getDate("DUEDATE")));
					cta.setEscalated(rs.getBoolean("ESCALATED"));
					cta.setNote(rs.getString("NOTE"));
					cta.setPriority(rs.getString("PRIORITY"));
					cta.setReason(rs.getString("REASON"));
					cta.setSnoozeperiod(new DateTime(rs.getDate("SNOOZEPERIOD")));
					cta.setSnoozeReason(rs.getString("SNOOZEREASON"));
					cta.setSource(rs.getString("SOURCE"));
					cta.setStatus(rs.getString("STATUS"));
					ctas.add(cta);
				}
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}

			return ctas;
		} catch (SQLException e) {
			throw new DAOException("", e);
		} finally {
			if (getAllCTAs != null) {
				try {
					getAllCTAs.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}

		}
	}

}