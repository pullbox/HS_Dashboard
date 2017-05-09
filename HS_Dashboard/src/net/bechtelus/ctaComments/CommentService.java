package net.bechtelus.ctaComments;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.bechtelus.util.HSDashboardUtility;

@Named
@ApplicationScoped
public class CommentService {

	public Comment find(String id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.find(Comment.class, id);
		} finally {
			em.close();
		}

	}

	public List<Comment> list() {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			return em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Comment> getCommentsByCTA(String cta_id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			Query query = em.createNamedQuery("commentsByCTA");
			query.setParameter("cta_ID", cta_id);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public void create(Comment comment) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(comment);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void update(Comment comment) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();
		try {
			em.getTransaction().begin();
			Comment acomment = em.find(Comment.class, comment.getId());
			acomment.setComment(comment.getComment());
			acomment.setCreateby(comment.getCreateby());
			acomment.setCreatedDate(comment.getCreatedDate());
			acomment.setCta_id(comment.getCta_id());
			acomment.setModifiedby(comment.getModifiedby());
			acomment.setModifiedDate(comment.getModifiedDate());
			acomment.setOwner_id(comment.getOwner_id());
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(long id) {
		EntityManager em = HSDashboardUtility.getEMF().createEntityManager();

		try {
			em.getTransaction().begin();
			Comment acomment = em.find(Comment.class, id);
			em.remove(acomment);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public CommentService() {
		super();
	}

}
