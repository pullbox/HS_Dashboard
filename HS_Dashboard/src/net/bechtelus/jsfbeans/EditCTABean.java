package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Named;





@Named("editCTABean")
@SessionScoped
public class EditCTABean implements Serializable  {
	private static final Log logger = LogFactory.getLog(EditCTABean.class);
	private static final long serialVersionUID = 7778898875625989495L;
	
	private CallToAction cta;

	
	
	
	
	public Long getID() {
		return cta.getId();
	}

	public String getDescription() {
		return cta.getDescription();
	}

	public User getAssignee() {
		return cta.getAssignee();
	}

	public String getStatus() {
		return cta.getStatus();
	}

	public String getType() {
		return cta.getCtaType();
	}

	public String getPriority() {
		return cta.getPriority();
	}

	public String getReason() {
		return cta.getReason();
	}

	public String getSnoozeReason() {
		return cta.getSnoozeReason();
	}

	public String getCreatedDate() {
		return cta.getCreatedDate();
	}

	public String getSnoozePeriod() {
		return cta.getSnoozeperiod();
	}

	public String getModifiedDate() {
		return cta.getModifiedDate();
	}
	public String getDueDate() {
		return cta.getDueDate();
	}
	
	public String getCreatedBy() {
		returnb cta.getCreatedBy();
	}
	
	public String getModifiedBy() {
		return cta.getModifiedBy();
	}
	public String getNote() {
		return cta.getNote();
	}
	
	public Boolean getIsEscalated() {
		return cta.isEscalated();
	}
	
	
}
