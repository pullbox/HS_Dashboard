package net.bechtelus.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import net.bechtelus.common.AbstractMSSQLDAO;
import net.bechtelus.common.DAOException;
import net.bechtelus.user.User;

public class MSSQLUserDAO extends AbstractMSSQLDAO implements UserDAO, Serializable {
	private static final String SQL_SELECT_USER_BY_USER_TK = "SELECT * from dbo.sf_user WHERE USER_TK = ?";
	private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * from dbo.sf_user WHERE EMAIL = ?";
	private static final String SQL_SELECT_USER_BY_USER_ID = "SELECT * from dbo.sf_user WHERE USER_ID = ?";
	private static final String SQL_SELECT_ALL_ACTIVE_USERS = "SELECT * from dbo.sf_user WHERE ACTIVE = 1";

	private static final Log logger = LogFactory.getLog(MSSQLUserDAO.class);

	public MSSQLUserDAO(Connection sqlConnection) throws DAOException {
		super(sqlConnection);
	}

	@Override
	public User findUserById(long user_tk) throws DAOException {

		PreparedStatement findUserById = null;
		try {
			findUserById = sqlConnection.prepareStatement(SQL_SELECT_USER_BY_USER_TK);
			findUserById.setLong(1, user_tk);
			ResultSet rs = findUserById.executeQuery();

			if (rs.next()) {
				try {
					User user = new User();
					user = read(rs);
					return user;
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
			if (findUserById != null) {
				try {
					findUserById.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email) throws DAOException {
		PreparedStatement findUserByEmail = null;
		try {
			findUserByEmail = sqlConnection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
			findUserByEmail.setString(1, email);
			ResultSet rs = findUserByEmail.executeQuery();

			if (rs.next()) {
				try {
					User user = new User();
					user = read(rs);
					return user;
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
			if (findUserByEmail != null) {
				try {
					findUserByEmail.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
		return null;
	}

	@Override
	public User findUserByUserID(String user_id) throws DAOException {
		PreparedStatement findUserUserid = null;
		try {
			findUserUserid = sqlConnection.prepareStatement(SQL_SELECT_USER_BY_USER_ID);
			findUserUserid.setString(1, user_id);
			ResultSet rs = findUserUserid.executeQuery();

			if (rs.next()) {
				try {
					User user = new User();
					user = read(rs);
					return user;
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
			if (findUserUserid != null) {
				try {
					findUserUserid.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}
		}
		return null;
	}

	@Override
	public List<User> getAllActiveUsers() throws DAOException {
		PreparedStatement getAllCTAs = null;

		try {
			getAllCTAs = sqlConnection.prepareStatement(SQL_SELECT_ALL_ACTIVE_USERS);
			ResultSet rs = getAllCTAs.executeQuery();
			List<User> users = new ArrayList<User>();

			try {
				while (rs.next()) {

					User user = new User();

					user = read(rs);
					users.add(user);
				}
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.debug("", e);
				}
			}

			return users;
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

	private User read(ResultSet rs) throws SQLException {

		Long USER_TK = rs.getLong("USER_TK");
		String USER_ID = rs.getString("USER_ID");
		String EMAIL = rs.getString("EMAIL");
		String FULL_NAME = rs.getString("FULL_NAME");
		String TITLE = rs.getString("TITLE");
		Boolean ACTIVE = rs.getBoolean("ACTIVE");

		User user = new User();
		user.setACTIVE(ACTIVE);
		user.setEMAIL(EMAIL);
		user.setFULL_NAME(FULL_NAME);
		user.setTITLE(TITLE);
		user.setUSER_ID(USER_ID);
		user.setUSER_TK(USER_TK);

		return user;
	}

}