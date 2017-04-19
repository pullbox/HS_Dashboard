package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.navigation.NavigationBean;
import net.bechtelus.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.*;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
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

	// This stores the result of a modification operation. There are predefined
	// messages for success and failure.
	protected String ctaModificationResult;
	protected static final String SUCCESS = "Success";

	// reference for database access
	@Inject
	private CallToActionService ctaservice;

	@PostConstruct
	public void init() {
		try {
			this.cta = new CallToAction();
			//this.cta.setCreatedDate(new Date());
			this.ctaOperation = CREATE_OPERATION;
		} catch (RuntimeException ex) {
			handleException(ex);
		}
	}

	public void deleteCTA() {
		
		
	}

	public void saveCTAActionListener(ActionEvent actionEvent) {

		try {
			if (this.ctaOperation == this.UPDATE_OPERATION) {
				ctaservice.update(this.cta);
			} else {
				ctaservice.create(cta);
				//navigation.redirectToWelcome();
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
		}
	}

	public List<CallToAction> getCallToActionsForUser(String assignee_user_id) {
		try {
			if (this.ctas == null) {
				ctas = ctaservice.getCTAsByAssignee(assignee_user_id);
			}

		} catch (RuntimeException ex) {
			handleException(ex);
		}
		return this.ctas;
	}

	public void deleteCTAActionListener(ActionEvent actionEvent) {
		try {
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
		}
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
		return cta.getCreatedDate();
	}

	public void setCreatedDate(Date date) {
		cta.setCreatedDate(date);
	}

	public Date getDueDate() {
		return cta.getDueDate();
	}

	public void setDueDate(Date aDate) {
		cta.setDueDate(aDate);
	}

	public Date getSnoozePeriod() {
		if (cta.getSnoozeperiod() != null) {
			return cta.getSnoozeperiod();
		} else {
			return null;
		}

	}

	public void setSnoozePeriod(Date date) {
		cta.setSnoozeperiod(date);
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
