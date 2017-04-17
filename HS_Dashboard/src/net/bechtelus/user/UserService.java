package net.bechtelus.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;
	

	
	public User find(Long id) {
		return em.find(User.class, id);
	}
	
	   public List<User> list() {
	        return em.createQuery("SELECT c FROM CS_HS_CallToAction c", User.class).getResultList();
	    }
	
	/*
	 public void create(User cta) {
	        em.persist(cta);
	    }
	
	
	  public void update(User cta) {
	        em.merge(cta);
	    }
	 
	  
	  public void delete(User cta) {
	        em.remove(em.contains(cta) ? cta : em.merge(cta));
	    }*/
	 
}
