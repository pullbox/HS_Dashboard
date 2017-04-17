package net.bechtelus.CTA;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CallToActionService {

	@PersistenceContext
	private EntityManager em;
	

	
	public CallToAction find(Long id) {
		return em.find(CallToAction.class, id);
	}
	
	   public List<CallToAction> list() {
	        return em.createQuery("SELECT c FROM CS_HS_CallToAction c", CallToAction.class).getResultList();
	    }
	
	
	 public void create(CallToAction cta) {
	        em.persist(cta);
	    }
	
	
	  public void update(CallToAction cta) {
	        em.merge(cta);
	    }
	 
	  
	  public void delete(CallToAction cta) {
	        em.remove(em.contains(cta) ? cta : em.merge(cta));
	    }
	 
}
