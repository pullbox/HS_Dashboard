package net.bechtelus.user;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import net.bechtelus.common.DAOException;
import net.bechtelus.util.HSDashboardUtility;

@Named
@ApplicationScoped
public class UserService implements UserDAO, Serializable {


	private EntityManager em;
	private User user, result;


	public User find(String user_id) {
		return em.find(User.class, user_id);
	}

	public List<User> list() {
		return em.createQuery("SELECT u FROM sf_user u", User.class).getResultList();
	}

	@Override
	public User findUserById(long user_tk) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByEmail(String email) throws DAOException {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();

		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.EMAIL = :email", User.class);
			query.setParameter("email", email);
			result = (User) query.getSingleResult();
			return result;
		} finally {
			em.close();
		}

	}

	@Override
	public User findUserByUserID(String user_id) throws DAOException {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.USER_ID = :userid", User.class);
			query.setParameter("userid", user_id);
			result = (User) query.getSingleResult();
			return result;
		} finally {
			em.close();
		}

	}

	@Override
	public List<User> getAllActiveUsers() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public void create(User cta) { em.persist(cta); }
	 * 
	 * 
	 * public void update(User cta) { em.merge(cta); }
	 * 
	 * 
	 * public void delete(User cta) { em.remove(em.contains(cta) ? cta :
	 * em.merge(cta)); }
	 */

	
	
}
