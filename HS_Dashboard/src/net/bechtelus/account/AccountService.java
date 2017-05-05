package net.bechtelus.account;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.bechtelus.util.HSDashboardUtility;

@Named
@ApplicationScoped
public class AccountService {

	public Account find(String id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.find(Account.class, id);
		} finally {
			em.close();
		}

	}

	public List<Account> list() {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.createQuery("SELECT c FROM Account c", Account.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Account> getAccountsByCSM(String CSM_user_id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			Query query = em.createNamedQuery("accountByCSM");
			query.setParameter("CSM_user_id", CSM_user_id);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	

	public List<Account> getAccountsByName(String account_NAME) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			Query query = em.createNamedQuery("accountByName");
			query.setParameter("account_NAME", "%" + account_NAME + "%");
			return query.getResultList();
		} finally {
			em.close();
		}

	}
	
	

	public List<Account> getAccountsByEAG(String EAG_user_id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			Query query = em.createNamedQuery("accountByEAG");
			query.setParameter("EAG_user_id", EAG_user_id);
			return query.getResultList();
		} finally {
			em.close();
		}

	}

	public void create(Account account) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void update(Account account) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			Account aaccount = em.find(Account.class, account.getACCOUNT_ID());
			aaccount.setACCOUNT_NAME(account.getACCOUNT_NAME());
			aaccount.setACCOUNT_TYPE(account.getACCOUNT_TYPE());
			aaccount.setACTIVE_SUBS_CUSTOMER(account.getACTIVE_SUBS_CUSTOMER());
			aaccount.setCUSTOMER_SUCCESS_MANAGER(account.getCUSTOMER_SUCCESS_MANAGER());
			aaccount.setCUSTOMER_TYPE(account.getCUSTOMER_TYPE());
			aaccount.setENTERPRISE_ARCHITECT_ASSIGNED(account.getENTERPRISE_ARCHITECT_ASSIGNED());
			aaccount.setHDS_CUSTOMER(account.getHDS_CUSTOMER());
			aaccount.setOWNER_ID(account.getOWNER_ID());
			aaccount.setSUB_END_DATE(account.getSUB_END_DATE());
			aaccount.setTECHNICAL_ACCOUNT_MANAGER(account.getTECHNICAL_ACCOUNT_MANAGER());
			aaccount.setZENDESK_ORG_ID(account.getZENDESK_ORG_ID());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();

		try {
			em.getTransaction().begin();
			Account aaccount = em.find(Account.class, id);
			em.remove(aaccount);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public AccountService() {
		super();
	}

}
