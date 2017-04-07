package net.bechtelus.CTA;

import static net.bechtelus.common.DAOUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import net.bechtelus.common.AbstractMSSQLDAO;
import net.bechtelus.common.DAOException;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.common.MySQLDAOFactory;
import net.bechtelus.util.HSDashboardUtility;

public class MSSQLCallToActionDAO extends AbstractMSSQLDAO implements CallToActionDAO {
	private static final String SQL_SELECT_CALLTOACTION_BY_ID = "SELECT * from CS_HS_CallToAction WHERE id = ?";
	private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD = "SELECT id, email, firstname, lastname, birthdate FROM User WHERE email = ? AND password = MD5(?)";
	private static final String SQL_LIST_ORDER_BY_ORDER = "SELECT id, sort_order, name, description, type, weight, always_applicable FROM items ORDER BY sort_order";
	private static final String SQL_INSERT = "INSERT INTO items (type, name, description, weight, sort_order, always_applicable) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE items SET name=?, type=?, description=?, weight=?, sort_order=?, always_applicable=? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM items WHERE id = ?";
	private static final String SQL_EXIST_EMAIL = "SELECT id FROM User WHERE email = ?";
	private static final String SQL_CHANGE_PASSWORD = "UPDATE User SET password = MD5(?) WHERE id = ?";
	private static final String SQL_INSERT_CALLTOACTION = "INSERT INTO CS_HS_CallToAction (ID, DESCRIPTION, ASSIGNEE, TYPE, STATUS, PRIOTITY, REASON, SNOOZEPERIOD, CTASTATUS, SOURCE, CREATEDBY, ESCALATED, DUEDATE, NOTE, CREATEDDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now()); SELECT LAST_INSERT_ID();"; 
    

	private static final Log logger = LogFactory.getLog(MSSQLCallToActionDAO.class);
	private DAOFactory mssqlFactory;

	public MSSQLCallToActionDAO(Connection sqlConnection) throws DAOException {
		super(sqlConnection);
	}



/*	public List<CallToAction> getCallToActions(String userId, boolean top, int maxTop, int maxDefault)
			throws DAOException {
		PreparedStatement selectCTAsById = null;
		try {
			String sqlQuery;
			if (top) {
				sqlQuery = "SELECT " + SQL_SELECT_ORDERS_BY_ID + " LIMIT " + maxTop;
			} else {
				sqlQuery = "SELECT " + SQL_SELECT_ORDERS_BY_ID + " LIMIT " + maxDefault;
			}
			selectOrdersById = sqlConnection.prepareStatement(sqlQuery);
			selectOrdersById.setString(1, userId);
			ResultSet rs = selectOrdersById.executeQuery();
			List<Order> orders = new ArrayList<Order>();

			try {
				while (rs.next()) {
					int orderId = rs.getInt(1);
					Calendar openDate = StockTraderUtility.convertToCalendar(rs.getDate(4));
					Calendar completionDate = null;
					try {
						if (rs.getDate(5) != null) {
							completionDate = StockTraderUtility.convertToCalendar(rs.getDate(5));
						} else {
							completionDate = Calendar.getInstance();
							completionDate.setTimeInMillis(0);
						}
					} catch (SQLException e) {
						logger.debug("", e);
						completionDate = Calendar.getInstance();
						completionDate.setTimeInMillis(0);
					}

					Order orderBean = new Order(orderId, rs.getString(2), rs.getString(3), openDate, completionDate,
							rs.getDouble(6), rs.getBigDecimal(7), rs.getBigDecimal(8), rs.getString(9));
					orders.add(orderBean);
				}

			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
			return orders;

		} catch (SQLException e) {
			throw new DAOException("", e);
		} finally {
			if (selectOrdersById != null) {
				try {
					selectOrdersById.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
	}
*/
	/*@Override
	public List<CallToAction> listCallToActions() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<CallToAction> ctas = new ArrayList<CallToAction>();

		try {
			con = mssqlFactory.createConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_LIST_ORDER_BY_ORDER);
			if (rs == null) {
				throw new DAOException("No Data");
			}

			try {
				while (rs.next()) {
					CallToAction cta = new CallToAction();
					cta.setId(rs.getLong("id"));
					cta.setApplicable(rs.getBoolean("always_applicable"));
					cta.setDescription(rs.getString("description"));
					cta.setName(rs.getString("name"));
					cta.setOrder(rs.getInt("sort_order"));
					cta.setType(rs.getString("type"));
					cta.setWeight(rs.getInt("weight"));

					ctas.add(cta);
				}
			} catch (Exception e) {
				throw new DAOException("ARGHh Something went wrong! MSSQLCallToActionDAO", e);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, stmt, rs);
		}
		return ctas;
	}
*/
	

	/*@Override
	public void update(CallToAction standard) throws IllegalArgumentException, DAOException {
		if (cta.getId() == 0) {
			throw new IllegalArgumentException("No ID! Something went terribly wrong!");
		}

		Object[] values = { cta.getName(), cta.getType(), cta.getDescription(), cta.getWeight(), cta.getOrder(),
				cta.isApplicable() };

		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet generatedkeys = null;

		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_UPDATE, true, values);

			int effectedrows = prpstmt.executeUpdate();

			if (effectedrows == 0) {
				throw new DAOException("Update failed as no rows were effected.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, generatedkeys);
		}
	}*/

/*	@Override
	public void delete(int id) {
		if (id == 0) {
			throw new IllegalArgumentException("Key is not set. ID is 0.");
		}

		Object[] values = { id };

		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet generatedkeys = null;

		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_DELETE, true, values);

			int effectedrows = prpstmt.executeUpdate();

			if (effectedrows == 0) {
				throw new DAOException("Deleting CallToAction failed, no rows affected.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, generatedkeys);
		}

	}*/

	/*@Override
	public CallToAction findbyKey(long aid) {
		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet rs = null;
		CallToAction cta = null;
		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_FIND_BY_ID, true, aid);

			rs = prpstmt.executeQuery();

			if (rs == null) {
				throw new DAOException("No Records fount!");
			}

			try {
				while (rs.next()) {
					cta = new CallToAction();
					cta.setId(rs.getLong("id"));
					cta.setApplicable(rs.getBoolean("always_applicable"));
					cta.setDescription(rs.getString("description"));
					cta.setName(rs.getString("name"));
					cta.setOrder(rs.getInt("sort_order"));
					cta.setType(rs.getString("type"));
					cta.setWeight(rs.getInt("weight"));

				}
			} catch (Exception e) {
				throw new DAOException("ARGHh Something went wrong! MSSQLCallToActionDAO", e);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, rs);
		}
		return cta;

	}*/

	@Override
	public boolean insertCTA(CallToAction cta) throws DAOException {

		PreparedStatement insertCTA = null;
		boolean insertSuccess = false;
		try {
								
			insertCTA = sqlConnection.prepareStatement(SQL_INSERT_CALLTOACTION);
			insertCTA.setLong(1,cta.getId());
			insertCTA.setString(2, cta.getDescription());
			insertCTA.setString(3, cta.getAssignee().getSlf_user_id());
			insertCTA.setString(4, cta.getCtaType());
			insertCTA.setString(5, cta.getStatus());
			insertCTA.setString(6, cta.getPriority());
			insertCTA.setString(7, cta.getReason());
			insertCTA.setDate(8, new java.sql.Date(cta.getSnoozeperiod().getMillis()));
			insertCTA.setString(9, cta.getCtaStatus());
			insertCTA.setString(10, cta.getSource());
			insertCTA.setString(11, cta.getCreateby().getSlf_user_id());
			insertCTA.setBoolean(12, cta.isEscalated());
			insertCTA.setDate(13, new java.sql.Date(cta.getDueDate().getMillis()));
			insertCTA.setString(14, cta.getNote());
			
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
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCTA(int id) throws DAOException {
		// TODO Auto-generated method stub

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
					//	cta.setAssignee(rs.getString("ASSIGNEE"));
					//	cta.setCreateby(rs.getString("CREATEDBY"));
						cta.setCreatedDate(new DateTime(rs.getDate("CREATEDDATE")));
						cta.setCtaStatus(rs.getString("CTASTATUS"));
						cta.setCtaType(rs.getString("CTATYPE"));
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
	         
	         
	         
	         
	         
	         
	         
	         
		


	@Override
	public List<CallToAction> getCallToActions(String userId, boolean top, int maxTop, int maxDefault)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}