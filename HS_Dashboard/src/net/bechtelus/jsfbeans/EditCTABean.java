package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
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

@Named("editCTABean")
@ViewScoped
public class EditCTABean implements Serializable {
	private static final Log logger = LogFactory.getLog(EditCTABean.class);
	private static final long serialVersionUID = 7778898875625989495L;

	@Inject
	NavigationBean navigation;

	// Current Call To Action
	private CallToAction cta;

	// caches the current list of ctas
	private List<CallToAction> ctas;

	// stores the current modification
	protected static final String UPDATE_OPERATION = "Update";
	protected static final String CREATE_OPERATION = "Create";
	protected String ctaOperation;
	private User user;
	private String id;

	// This stores the result of a modification operation. There are predefined
	// messages for success and failure.
	protected String ctaModificationResult;
	protected static final String SUCCESS = "Success";

	// reference for database access
	@Inject
	private CallToActionService ctaservice;
	@Inject
	private UserService userservice;

	@PostConstruct
	public void init() {

		String ctaID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ctaid");
		String userName =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");

		System.out.println("CTAID: " + ctaID);
		System.out.println("userName: " + userName);

		this.user = getUser(userName);

		if (ctaID == null) {
			try {
				this.cta = new CallToAction();
				// this.cta.setCreatedDate(new Date());
				this.ctaOperation = CREATE_OPERATION;
				this.cta.setSource("Manual");
				this.cta.setPriority("Medium");
				this.cta.setStatus("New");
				this.cta.setCreatedDate(new Date());
				this.cta.setCreateby(user);

			} catch (RuntimeException ex) {
				handleException(ex);
			}
		} else {
			try {
				ctaservice = new CallToActionService();
				this.cta = ctaservice.find(Long.valueOf(ctaID));
				this.ctaOperation = UPDATE_OPERATION;
				this.cta.setModifiedby(user);
				this.cta.setModifiedDate(new Date());
			} catch (RuntimeException ex) {
				handleException(ex);
			} finally {
				ctaservice = null;
			}
		}

		logger.info(cta.toString());

	}

	public void deleteCTA() {

	}

	public void saveCTAActionListener(ActionEvent actionEvent) {

		try {
			ctaservice = new CallToActionService();
			if (this.ctaOperation == this.UPDATE_OPERATION) {
				ctaservice.update(this.cta);
			} else {
				ctaservice.create(this.cta);
				// navigation.redirectToWelcome();
			}
			this.ctaModificationResult = SUCCESS;
		} catch (RollbackException ex) {
			if (ex.getCause() instanceof OptimisticLockException) {
				this.ctaModificationResult = "Failed to " + this.ctaOperation.toLowerCase()
						+ " CalltoAction. CTA status has changed since last viewed";
				logger.debug(" " + ctaModificationResult);
			} else {
				this.ctaModificationResult = "Failed to " + this.ctaOperation.toLowerCase()
						+ " CalltoAction. An unexpected Error occurred: " + ex.toString();
				logger.debug(" " + ctaModificationResult);
				handleException(ex);
			}
		} catch (Exception ex) {
			this.ctaModificationResult = "Failed to " + this.ctaOperation.toLowerCase()
					+ " CalltoAction. An unexpected Error occurred: " + ex.toString();
			logger.debug(" " + ctaModificationResult);
		} finally {
			ctaservice = null;
		}
	}

	public List<CallToAction> getCallToActionsForUser(String assignee_user_id) {
		try {
			ctaservice = new CallToActionService();
			if (this.ctas == null) {
				ctas = ctaservice.getCTAsByAssignee(assignee_user_id);
			}

		} catch (RuntimeException ex) {
			handleException(ex);
		} finally {
			ctaservice = null;
		}
		return this.ctas;
	}

	public void deleteCTAActionListener(ActionEvent actionEvent) {
		try {
			ctaservice = new CallToActionService();
			ctaservice.delete(this.cta.getId());
			this.ctaModificationResult = SUCCESS;

		} catch (OptimisticLockException ex) {
			this.ctaModificationResult = "Failed to " + this.ctaOperation.toLowerCase()
					+ " CallToAction.  CTA status has changed since last viewed";
			logger.debug(" " + ctaModificationResult);
		} catch (Exception ex) {
			this.ctaModificationResult = "Failed to " + this.ctaOperation.toLowerCase()
					+ " CallToAction.  An unexpected Error ocurred: " + ex.toString();
			logger.debug(" " + ctaModificationResult);
		} finally {
			ctaservice = null;
		}
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

	public String getStatus() {
		return cta.getStatus();
	}

	public void setStatus(String status) {
		cta.setStatus(status);
	}

	public String getType() {
		return cta.getType();
	}

	public void setType(String type) {
		cta.setType(type);
	}

	public String getSource() {
		return cta.getSource();
	}

	public void setSource(String source) {
		cta.setSource(source);
	}

	public String getPriority() {
		return cta.getPriority();
	}

	public void setPriority(String priority) {
		cta.setPriority(priority);
	}

	public String getReason() {
		return cta.getReason();
	}

	public void setReason(String reason) {
		cta.setReason(reason);
	}

	public String getSnoozeReason() {
		return cta.getSnoozeReason();
	}

	public void setSnoozeReason(String snoozeReason) {
		cta.setSnoozeReason(snoozeReason);
	}

	public Date getCreatedDate() {
		if (cta.getCreatedDate() != null) {
			return cta.getCreatedDate();
		} else {
			return null;
		}

	}

	public void setCreatedDate(Date date) {
		if (date != null) {
			cta.setCreatedDate(date);
		}
	}

	public Date getDueDate() {
		if (cta.getDueDate() != null) {
			return cta.getDueDate();
		} else {
			return null;
		}
	}

	public void setDueDate(Date aDate) {
		if (aDate != null) {
			cta.setDueDate(aDate);
		}
	}

	public Date getSnoozePeriod() {
		if (cta.getSnoozeperiod() != null) {
			return cta.getSnoozeperiod();
		} else {
			return null;
		}

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public void setSnoozePeriod(Date date) {
		if (date != null) {
			cta.setSnoozeperiod(date);
		}
	}

	public Date getModifiedDate() {
		if (cta.getModifiedDate() != null) {
			return cta.getModifiedDate();
		} else {
			return null;
		}
	}

	public String getCreatedBy() {
		User user = new User();
		user = cta.getCreateby();
		return user.getFULL_NAME();
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

	public int getImpact() {
		return cta.getImpact();
	}

	public void setImpact(int impact) {
		cta.setImpact(impact);
	}

	public Boolean getIsEscalated() {
		return cta.isEscalated();
	}

	public void setIsEscalated(Boolean escalated) {
		cta.setEscalated(escalated);
	}

	/**
	 * @return the ctaOperation
	 */
	public String getCtaOperation() {
		return ctaOperation;
	}

	/**
	 * @param ctaOperation
	 *            the ctaOperation to set
	 */
	public void setCtaOperation(String ctaOperation) {
		this.ctaOperation = ctaOperation;
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

	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return ctaModificationResult == SUCCESS;
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
