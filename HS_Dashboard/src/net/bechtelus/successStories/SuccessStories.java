package net.bechtelus.successStories;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import net.bechtelus.account.Account;
import net.bechtelus.milestone.BasicMileStone;
import net.bechtelus.user.User;

/**
 * @author dbechtel
 *
 */

@Entity
@Table(name = "SuccessStories")
@NamedQueries({

		@NamedQuery(name = "cssByAccount", query = "Select c from SuccessStories c where c.account = :account_id") })
public class SuccessStories implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906760585094374728L;

	@Id
	@TableGenerator(name = "TABLE_CSMS", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CSMS", allocationSize = 1)
	@GeneratedValue(generator = "TABLE_MStone", strategy = GenerationType.TABLE)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	private String name;
	private String description;
	private Boolean template;
	
	@ManyToOne
	@JoinColumn(name = "ACCOUNT")
	private Account account;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private java.util.Calendar startTime;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private java.util.Calendar endTime;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public Date getCreatedDate() {
		if (this.createdDate == null) {
			return null;
		} else {

			return this.createdDate.getTime();
		}
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date acreatedDate) {
		if (acreatedDate == null) {
			this.createdDate = null;
		} else {
			Calendar cal = new GregorianCalendar();
			cal.setTime(acreatedDate);
			this.createdDate = cal;
		}
	}
	
	
	/**
	 * @return the createdDate
	 
	public Date getstartTime() {
		if (this.startTime == null) {
			return null;
		} else {

			return this.startTime.getTime();
		}
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	
	public void setstartTime(Date aStartTime) {
		if (aStartTime == null) {
			this.startTime = null;
		} else {
			Calendar cal = new GregorianCalendar();
			cal.setTime(aStartTime);
			this.startTime = cal;
		}
	}
	
	
	
	
	/**
	 * @return the createdDate
	
	public Date getendTime() {
		if (this.endTime == null) {
			return null;
		} else {

			return this.endTime.getTime();
		}
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 
	public void setendTime(Date aendTime) {
		if (aendTime == null) {
			this.endTime = null;
		} else {
			Calendar cal = new GregorianCalendar();
			cal.setTime(aendTime);
			this.endTime = cal;
		}
	}
	
	 */	

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		if (this.modifiedDate == null) {
			return null;
		} else {
			return this.modifiedDate.getTime();
		}
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date amodifiedDate) {
		if (amodifiedDate == null) {
			this.modifiedDate = null;
		} else {
			Calendar cal = new GregorianCalendar();
			cal.setTime(amodifiedDate);
			this.modifiedDate =cal;
		}
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

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	

	public Boolean isTemplate() {
		return template;
	}

	public void setTemplate(Boolean template) {
		this.template = template;
	}

	public SuccessStories() {
		super();
	}


	public SuccessStories(String name, Boolean template) {
		super();
		this.name = name;
		this.template = template;
		
	}
	
	
	@Override
	public String toString() {
		return "SuccessStories [id=" + id + ", name=" + name + "]";
	}
}
