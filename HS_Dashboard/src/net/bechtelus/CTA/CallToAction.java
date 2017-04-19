package net.bechtelus.CTA;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;
import net.bechtelus.user.User;

/**
 * @author dbechtel
 *
 */

@Entity
@Table(name="CS_HS_CallToActions")
@NamedQueries({
	
	@NamedQuery(
			name="ctaByID",
			query="Select c from CallToAction c where c.id = :ctaid"
			),
	
	@NamedQuery(
			name="ctasByAssignee",
			query="Select c from CallToAction c where c.assignee = :assignee_user_id"
			)
})
public class CallToAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906760585094374728L;

	@Id
	@SequenceGenerator(name="CTA_SEQ_GEN", sequenceName="seqCTA", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CTA_SEQ_GEN")
	private long id;
	private String description;
	
	
	@OneToOne
	@JoinColumn(name="ASSIGNEE")
	private User assignee;
	private String type; // Expansion, Risk, Lifecycle
	private String status; // New, WIP, Waiting on customer, Escalated,
							// deferred, ...
	private String priority; // High, Medium, Low, Blue
	private String reason; // payment issue, lost champion, support risk,
							// training issue, adoption issue
	private String snoozeReason; // Waiting on product feature, User on
									// vacation, other

	private DateTime snoozeperiod; //
	private String source; // Manual, Rule, ...
	
	@OneToOne
	@JoinColumn(name="CREATEDBY")
	private User createby;
	private DateTime createdDate;
	private boolean escalated;
	private DateTime dueDate;
	private String note;
	private DateTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name="MODIFIEDBY")
	private User modifiedby;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param description
	 *            the description to set
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
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	/**
	 * Expansion, Risk, Lifecycle
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String ctaType) {
		this.type = ctaType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * New, WIP, Waiting on customer, Escalated, deferred, ...
	 * 
	 * @param status
	 *            the status to set
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
	 * @param priority
	 *            the priority to set
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
	 * payment issue, lost champion, support risk, training issue, adoption
	 * issue
	 * 
	 * @param reason
	 *            the reason to set
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
	 * Waiting on product feature, User on vacation, other
	 * 
	 * @param snoozeReason
	 *            the snoozeReason to set
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
	 * @param snoozeperiod
	 *            the snoozeperiod to set
	 */
	public void setSnoozeperiod(DateTime snoozeperiod) {
		this.snoozeperiod = snoozeperiod;
	}

	

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Manual, Rule, ...
	 * 
	 * @param source
	 *            the source to set
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
	 * @param createby
	 *            the createby to set
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
	 * @param createdDate
	 *            the createdDate to set
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
	 * @param escalated
	 *            the escalated to set
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
	 * @param dueDate
	 *            the dueDate to set
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
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the modifiedDate
	 */
	public DateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(DateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedby
	 */
	public User getModifiedby() {
		return modifiedby;
	}

	/**
	 * @param modifiedby
	 *            the modifiedby to set
	 */
	public void setModifiedby(User modifiedby) {
		this.modifiedby = modifiedby;
	}

	public CallToAction() {
		super();
	}
	
	 @Override
	   public String toString() {
	      return "CTA [id=" + id + ", name=" + description + ", source=" + source + ", type=" + type + "]";
	   }
}
