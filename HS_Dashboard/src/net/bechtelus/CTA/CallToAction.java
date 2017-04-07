package net.bechtelus.CTA;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import net.bechtelus.user.User;

/**
 * @author dbechtel
 *
 */
public class CallToAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906760585094374728L;

	private String description;
	private User assignee;
	private String ctaType;  		// Expansion, Risk, Lifecycle
	private String status;			// New, WIP, Waiting on customer, Escalated, deferred, ...
	private String priority;		// High, Medium, Low, Blue
	private String reason;			// payment issue, lost champion, support risk, training issue, adoption issue
	private String snoozeReason;	// Waiting on product feature, User on vacation, other
	private long id;
	private DateTime snoozeperiod;	//
	private String ctaStatus;  		// Closed - no action
	private String source;			// Manual, Rule, ...
	private User createby;
	private DateTime createdDate;
	private boolean escalated;
	private DateTime dueDate;
	private	String note;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the assignee
	 */
	public User getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the ctaType
	 */
	public String getCtaType() {
		return ctaType;
	}

	/**
	 * @param ctaType the ctaType to set
	 */
	public void setCtaType(String ctaType) {
		this.ctaType = ctaType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the snoozeReason
	 */
	public String getSnoozeReason() {
		return snoozeReason;
	}

	/**
	 * @param snoozeReason the snoozeReason to set
	 */
	public void setSnoozeReason(String snoozeReason) {
		this.snoozeReason = snoozeReason;
	}

	/**
	 * @return the snoozeperiod
	 */
	public DateTime getSnoozeperiod() {
		return snoozeperiod;
	}

	/**
	 * @param snoozeperiod the snoozeperiod to set
	 */
	public void setSnoozeperiod(DateTime snoozeperiod) {
		this.snoozeperiod = snoozeperiod;
	}

	/**
	 * @return the ctaStatus
	 */
	public String getCtaStatus() {
		return ctaStatus;
	}

	/**
	 * @param ctaStatus the ctaStatus to set
	 */
	public void setCtaStatus(String ctaStatus) {
		this.ctaStatus = ctaStatus;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the createby
	 */
	public User getCreateby() {
		return createby;
	}

	/**
	 * @param createby the createby to set
	 */
	public void setCreateby(User createby) {
		this.createby = createby;
	}

	/**
	 * @return the createdDate
	 */
	public DateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the escalated
	 */
	public boolean isEscalated() {
		return escalated;
	}

	/**
	 * @param escalated the escalated to set
	 */
	public void setEscalated(boolean escalated) {
		this.escalated = escalated;
	}

	/**
	 * @return the dueDate
	 */
	public DateTime getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}



	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public CallToAction() {

	}
}
