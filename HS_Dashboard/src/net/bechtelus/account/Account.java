package net.bechtelus.account;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import net.bechtelus.user.User;

@Entity
@Table(name = "sf_account")

@NamedQueries({

		@NamedQuery(name = "accountByCSM", query = "Select c from Account c where c.CUSTOMER_SUCCESS_MANAGER = :CSM_user_id"),
		@NamedQuery(name = "accountByName", query = "Select c from Account c where c.ACCOUNT_NAME like :account_NAME AND c.ACTIVE_SUBS_CUSTOMER = 1" ),
		@NamedQuery(name = "accountByEAG", query = "Select c from Account c where c.ENTERPRISE_ARCHITECT_ASSIGNED = :EAG_user_id") })

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7219677042515322422L;

	private Long ACCOUNT_TK;

	@Id
	private String ACCOUNT_ID;
	private String ACCOUNT_NAME;
	private Boolean ACTIVE_SUBS_CUSTOMER;
	private Boolean HDS_CUSTOMER;
	private String ACCOUNT_TYPE;
	private String CUSTOMER_TYPE;
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private User OWNER_ID;
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_SUCCESS_MANAGER")
	private User CUSTOMER_SUCCESS_MANAGER;
	@ManyToOne
	@JoinColumn(name = "ENTERPRISE_ARCHITECT_ASSIGNED")
	private User ENTERPRISE_ARCHITECT_ASSIGNED;
	@ManyToOne
	@JoinColumn(name = "TECHNICAL_ACCOUNT_MANAGER")
	private User TECHNICAL_ACCOUNT_MANAGER;
	private Date SUB_END_DATE;
	private int ZENDESK_ORG_ID;

	/**
	 * @return the aCCOUNT_TK
	 */
	public Long getACCOUNT_TK() {
		return ACCOUNT_TK;
	}

	/**
	 * @param aCCOUNT_TK
	 *            the aCCOUNT_TK to set
	 */
	public void setACCOUNT_TK(Long aCCOUNT_TK) {
		ACCOUNT_TK = aCCOUNT_TK;
	}

	/**
	 * @return the aCCOUNT_ID
	 */
	public String getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	/**
	 * @param aCCOUNT_ID
	 *            the aCCOUNT_ID to set
	 */
	public void setACCOUNT_ID(String aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	/**
	 * @return the aCCOUNT_NAME
	 */
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}

	/**
	 * @param aCCOUNT_NAME
	 *            the aCCOUNT_NAME to set
	 */
	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}

	/**
	 * @return the aCTIVE_SUBS_CUSTOMER
	 */
	public Boolean getACTIVE_SUBS_CUSTOMER() {
		return ACTIVE_SUBS_CUSTOMER;
	}

	/**
	 * @param aCTIVE_SUBS_CUSTOMER
	 *            the aCTIVE_SUBS_CUSTOMER to set
	 */
	public void setACTIVE_SUBS_CUSTOMER(Boolean aCTIVE_SUBS_CUSTOMER) {
		ACTIVE_SUBS_CUSTOMER = aCTIVE_SUBS_CUSTOMER;
	}

	/**
	 * @return the hDS_CUSTOMER
	 */
	public Boolean getHDS_CUSTOMER() {
		return HDS_CUSTOMER;
	}

	/**
	 * @param hDS_CUSTOMER
	 *            the hDS_CUSTOMER to set
	 */
	public void setHDS_CUSTOMER(Boolean hDS_CUSTOMER) {
		HDS_CUSTOMER = hDS_CUSTOMER;
	}

	/**
	 * @return the aCCOUNT_TYPE
	 */
	public String getACCOUNT_TYPE() {
		return ACCOUNT_TYPE;
	}

	/**
	 * @param aCCOUNT_TYPE
	 *            the aCCOUNT_TYPE to set
	 */
	public void setACCOUNT_TYPE(String aCCOUNT_TYPE) {
		ACCOUNT_TYPE = aCCOUNT_TYPE;
	}

	/**
	 * @return the cUSTOMER_TYPE
	 */
	public String getCUSTOMER_TYPE() {
		return CUSTOMER_TYPE;
	}

	/**
	 * @param cUSTOMER_TYPE
	 *            the cUSTOMER_TYPE to set
	 */
	public void setCUSTOMER_TYPE(String cUSTOMER_TYPE) {
		CUSTOMER_TYPE = cUSTOMER_TYPE;
	}

	/**
	 * @return the oWNER_ID
	 */
	public User getOWNER_ID() {
		return OWNER_ID;
	}

	/**
	 * @param oWNER_ID
	 *            the oWNER_ID to set
	 */
	public void setOWNER_ID(User oWNER_ID) {
		OWNER_ID = oWNER_ID;
	}

	/**
	 * @return the cUSTOMER_SUCCESS_MANAGER
	 */
	public User getCUSTOMER_SUCCESS_MANAGER() {
		return CUSTOMER_SUCCESS_MANAGER;
	}

	/**
	 * @param cUSTOMER_SUCCESS_MANAGER
	 *            the cUSTOMER_SUCCESS_MANAGER to set
	 */
	public void setCUSTOMER_SUCCESS_MANAGER(User cUSTOMER_SUCCESS_MANAGER) {
		CUSTOMER_SUCCESS_MANAGER = cUSTOMER_SUCCESS_MANAGER;
	}

	/**
	 * @return the eNTERPRISE_ARCHITECT_ASSIGNED
	 */
	public User getENTERPRISE_ARCHITECT_ASSIGNED() {
		return ENTERPRISE_ARCHITECT_ASSIGNED;
	}

	/**
	 * @param eNTERPRISE_ARCHITECT_ASSIGNED
	 *            the eNTERPRISE_ARCHITECT_ASSIGNED to set
	 */
	public void setENTERPRISE_ARCHITECT_ASSIGNED(User eNTERPRISE_ARCHITECT_ASSIGNED) {
		ENTERPRISE_ARCHITECT_ASSIGNED = eNTERPRISE_ARCHITECT_ASSIGNED;
	}

	/**
	 * @return the tECHNICAL_ACCOUNT_MANAGER
	 */
	public User getTECHNICAL_ACCOUNT_MANAGER() {
		return TECHNICAL_ACCOUNT_MANAGER;
	}

	/**
	 * @param tECHNICAL_ACCOUNT_MANAGER
	 *            the tECHNICAL_ACCOUNT_MANAGER to set
	 */
	public void setTECHNICAL_ACCOUNT_MANAGER(User tECHNICAL_ACCOUNT_MANAGER) {
		TECHNICAL_ACCOUNT_MANAGER = tECHNICAL_ACCOUNT_MANAGER;
	}

	/**
	 * @return the sUB_END_DATE
	 */
	public Date getSUB_END_DATE() {
		return SUB_END_DATE;
	}

	/**
	 * @param sUB_END_DATE
	 *            the sUB_END_DATE to set
	 */
	public void setSUB_END_DATE(Date sUB_END_DATE) {
		SUB_END_DATE = sUB_END_DATE;
	}

	/**
	 * @return the zENDESK_ORG_ID
	 */
	public int getZENDESK_ORG_ID() {
		return ZENDESK_ORG_ID;
	}

	/**
	 * @param zENDESK_ORG_ID
	 *            the zENDESK_ORG_ID to set
	 */
	public void setZENDESK_ORG_ID(int zENDESK_ORG_ID) {
		ZENDESK_ORG_ID = zENDESK_ORG_ID;
	}

	public Account() {

	}

}