package net.bechtelus.csmstones;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.bechtelus.util.HSDashboardUtility;

@Named
@ApplicationScoped
public class SuccessStoriesService {

	public SuccessStories find(Long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.find(SuccessStories.class, id);
		} finally {
			em.close();
		}

	}

	public List<SuccessStories> list() {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.createQuery("SELECT c FROM SuccessStories c", SuccessStories.class).getResultList();
		} finally {
			em.close();
		}
	}
	
	
	public List<SuccessStories> getCSSByAccount(String account_id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			 Query query = em.createNamedQuery("cssByAccount");
			 query.setParameter("account_id", account_id);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
		

	public void create(SuccessStories css) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(css);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void update(SuccessStories css) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			SuccessStories acss = em.find(SuccessStories.class, css.getId());
			em.getTransaction().begin();	
			acss.setCreateby(css.getCreateby());
			acss.setAccount(css.getAccount());
			acss.setCreatedDate(css.getCreatedDate());
			acss.setDescription(css.getDescription());
			acss.setTemplate(css.isTemplate());
			acss.setModifiedby(css.getModifiedby());
			acss.setModifiedDate(css.getModifiedDate());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();

		try {
			em.getTransaction().begin();
			SuccessStories acss = em.find(SuccessStories.class, id);
			em.remove(acss);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public SuccessStoriesService() {
		super();
	}

}
