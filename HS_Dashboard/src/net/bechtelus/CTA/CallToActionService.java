package net.bechtelus.CTA;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.bechtelus.util.HSDashboardUtility;

@Named
@ApplicationScoped
public class CallToActionService {

	public CallToAction find(Long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.find(CallToAction.class, id);
		} finally {
			em.close();
		}

	}

	public List<CallToAction> list() {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.createQuery("SELECT c FROM CallToAction c", CallToAction.class).getResultList();
		} finally {
			em.close();
		}
	}
	
	
	public List<CallToAction> getCTAsByAssignee(String assignee_user_id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			 Query query = em.createNamedQuery("ctasByAssignee");
			 query.setParameter("assignee_user_id", assignee_user_id);
			return query.getResultList();
		} finally {
			em.close();
		}

	}

	public void create(CallToAction cta) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(cta);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void update(CallToAction cta) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			CallToAction acta = em.find(CallToAction.class, cta.getId());
			acta.setAssignee(cta.getAssignee());
			acta.setCreateby(cta.getCreateby());
			acta.setCreatedDate(cta.getCreatedDate());
			acta.setType(cta.getType());
			acta.setDescription(cta.getDescription());
			acta.setDueDate(cta.getDueDate());
			acta.setEscalated(cta.isEscalated());
			acta.setModifiedby(cta.getModifiedby());
			acta.setNote(cta.getNote());
			acta.setPriority(cta.getPriority());
			acta.setReason(acta.getReason());
			acta.setSnoozeperiod(cta.getSnoozeperiod());
			acta.setSnoozeReason(cta.getSnoozeReason());
			acta.setSource(cta.getSource());
			acta.setStatus(acta.getStatus());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();

		try {
			em.getTransaction().begin();
			CallToAction acta = em.find(CallToAction.class, id);
			em.remove(acta);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public CallToActionService() {
		super();
	}

}
