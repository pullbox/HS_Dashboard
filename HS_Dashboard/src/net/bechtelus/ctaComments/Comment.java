package net.bechtelus.ctaComments;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import net.bechtelus.CTA.CallToAction;
import net.bechtelus.user.User;

@Entity
@Table(name = "CTA_Comments")

@NamedQueries({

		@NamedQuery(name = "commentsByCTA", query = "Select c from Comment c where c.cta_id = :cta_ID")		 })

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8119162060402440833L;

	@Id
	@TableGenerator(name = "TABLE_Comment", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "COMMENT", allocationSize = 1)
	@GeneratedValue(generator = "TABLE_Comment", strategy = GenerationType.TABLE)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "CTA_ID")
	private CallToAction cta_id;

	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User owner_id;

	@ManyToOne
	@JoinColumn(name = "CREATEDBY")
	private User createby;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Calendar createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Calendar modifiedDate;
	@ManyToOne
	@JoinColumn(name = "MODIFIEDBY")
	private User modifiedby;
	@Version
	@Column(name = "VERSION")
	private int version;
	@Column(name = "COMMENT")
	private String comment;

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
	 * @return the cta_id
	 */
	public CallToAction getCta_id() {
		return cta_id;
	}

	/**
	 * @param cta_id the cta_id to set
	 */
	public void setCta_id(CallToAction cta_id) {
		this.cta_id = cta_id;
	}

	/**
	 * @return the owner_id
	 */
	public User getOwner_id() {
		return owner_id;
	}

	/**
	 * @param owner_id the owner_id to set
	 */
	public void setOwner_id(User owner_id) {
		this.owner_id = owner_id;
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
	public java.util.Calendar getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(java.util.Calendar createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public java.util.Calendar getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(java.util.Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedby
	 */
	public User getModifiedby() {
		return modifiedby;
	}

	/**
	 * @param modifiedby the modifiedby to set
	 */
	public void setModifiedby(User modifiedby) {
		this.modifiedby = modifiedby;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment() {

	}

	public String toString() {
		return "CTA_COMMENT [id=" + id + ", cta_ID=" + cta_id + ", comment=" + comment + ", created_BY=" + createby.getFULL_NAME() + "]";
	}

}