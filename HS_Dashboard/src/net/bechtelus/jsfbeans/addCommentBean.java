package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.ctaComments.Comment;
import net.bechtelus.ctaComments.CommentService;
import net.bechtelus.navigation.NavigationBean;
import net.bechtelus.user.User;
import net.bechtelus.user.UserService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("addCommentBean")
@ViewScoped
public class addCommentBean implements Serializable {

	private static final long serialVersionUID = 8576288637590449183L;
	private static final Log logger = LogFactory.getLog(addCommentBean.class);


	@Inject
	NavigationBean navigation;

	// Current Call To Action
	private CallToAction cta;

	private User user;
	private String ctaModificationResult;

	private String id;
	private Comment comment;
	private List<User> users;
	

	// reference for database access
	@Inject
	private CallToActionService ctaservice;
	@Inject
	private UserService userservice;
	@Inject
	private CommentService commentService;

	@PostConstruct
	public void init() {

		String ctaID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ctaid");
		String userName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("username");
		String userName2 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("userName");

		System.out.println("CTAID: " + ctaID);
		System.out.println("userName: " + userName);

		this.user = getUser(userName);

		try {
			ctaservice = new CallToActionService();
			this.cta = ctaservice.find(Long.valueOf(ctaID));
			this.cta.setModifiedby(user);
			this.cta.setModifiedDate(new Date());
		} catch (RuntimeException ex) {
			handleException(ex);
		} finally {
			ctaservice = null;
		}

		
		comment = new Comment();
		comment.setCta_id(cta);
		comment.setCreateby(user);
		comment.setCreatedDate(new Date());
		comment.setComment("Deferred: ");

		// logger.info(cta.toString());

	}

	public void saveComment(ActionEvent actionEvent) {

		try {
			ctaservice = new CallToActionService();
			ctaservice.update(this.cta);
		} catch (RollbackException ex) {
			if (ex.getCause() instanceof OptimisticLockException) {
				this.ctaModificationResult = "Failed to update "
						+ " CalltoAction. CTA status has changed since last viewed";
				logger.debug(" " + ctaModificationResult);
			} else {
				this.ctaModificationResult = "Failed to Update" + " CalltoAction. An unexpected Error occurred: "
						+ ex.toString();
				logger.debug(" " + ctaModificationResult);
				handleException(ex);
			}
		} catch (Exception ex) {
			this.ctaModificationResult = "Failed to Update" + " CalltoAction. An unexpected Error occurred: "
					+ ex.toString();
			logger.debug(" " + ctaModificationResult);
		} finally {
			ctaservice = null;

		}

		if (comment.getComment().length() > 0) {
			try {
				commentService = new CommentService();
				commentService.create(this.comment);
				RequestContext.getCurrentInstance().closeDialog(null);
			} catch (RollbackException ex) {
				if (ex.getCause() instanceof OptimisticLockException) {
					this.ctaModificationResult = "Failed to update "
							+ " Comment. Comment status has changed since last viewed";
					logger.debug(" " + ctaModificationResult);
				} else {
					this.ctaModificationResult = "Failed to Update" + " Comment. An unexpected Error occurred: "
							+ ex.toString();
					logger.debug(" " + ctaModificationResult);
					handleException(ex);
				}
			} catch (Exception ex) {
				this.ctaModificationResult = "Failed to Update" + " Comment. An unexpected Error occurred: "
						+ ex.toString();
				logger.debug(" " + ctaModificationResult);
			} finally {
				ctaservice = null;

			}

		}

	}

	public List<User> assigneecomplete(String query) {
		try {
			userservice = new UserService();
			users = userservice.getUsersByName(query);

		} catch (RuntimeException ex) {
			handleException(ex);
		} finally {
			userservice = null;
		}
		return this.users;
	}

	public User getUser(String email) {

		try {
			userservice = new UserService();
			user = userservice.findUserByEmail(email);
		} catch (Exception ex) {
			logger.debug(" " + ex.toString());
		} finally {
			userservice = null;
		}
		return user;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	public Long getID() {
		return cta.getId();
	}

	/**
	 * @return the deferComment
	 */
	public String getComment() {
		return comment.getComment();
	}

	/**
	 * @param deferComment
	 *            the deferComment to set
	 */
	public void setComment(String acomment) {
		this.comment.setComment(acomment);
	}

	public void setID(Long ID) {
		cta.setId(ID);
	}


	public String getDescription() {
		return cta.getDescription();
	}

	public void setDescription(String description) {
		cta.setDescription(description);
	}

	public User getAssignee() {
		return cta.getAssignee();
	}

	public void setAssignee(User assignee) {
		cta.setAssignee(assignee);
	}

	



	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Date getModifiedDate() {
		if (cta.getModifiedDate() != null) {
			return cta.getModifiedDate();
		} else {
			return null;
		}
	}

	public String getModifiedBy() {
		User user = new User();
		user = cta.getCreateby();
		return user.getFULL_NAME();
	}

	public String getNote() {
		return cta.getNote();
	}

	public void setNote(String note) {
		cta.setNote(note);
	}


	/**
	 * @return the ctaModificationResult
	 */
	public String getCtaModificationResult() {
		return ctaModificationResult;
	}

	/**
	 * @param ctaModificationResult
	 *            the ctaModificationResult to set
	 */
	public void setCtaModificationResult(String ctaModificationResult) {
		this.ctaModificationResult = ctaModificationResult;
	}

	// This method is used to handle exceptions and display cause to user.
	public void handleException(RuntimeException ex) {
		StringBuffer details = new StringBuffer();
		Throwable causes = ex;
		while (causes.getCause() != null) {
			details.append(ex.getMessage());
			details.append("    Caused by:");
			details.append(causes.getCause().getMessage());
			causes = causes.getCause();
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
