package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.apache.commons.logging.Log;
import net.bechtelus.CTA.*;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("editCTABean")
@SessionScoped
public class EditCTABean implements Serializable {
	private static final Log logger = LogFactory.getLog(EditCTABean.class);
	private static final long serialVersionUID = 7778898875625989495L;

	private CallToAction cta;

	public void saveCTAAction(ActionEvent actionEvent) {
		if (saveCTA()) {
			addMessage("CTA " + cta.getDescription() + " was saved!");
		} else {
			addMessage("Houstan we have a problem");
		}

	}

	public void deleteCTAAction(ActionEvent actionEvent) {
		deleteCTA();
		addMessage("CTA " + cta.getDescription() + " was saved!");

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
		return cta.getCtaType();
	}

	public void setType(String type) {
		cta.setCtaType(type);
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
		return cta.getCreatedDate().toDate();
	}

	public void setCreatedDate(Date date) {
		cta.setCreatedDate(new DateTime(date));
	}

	public Date getDueDate() {
		return cta.getDueDate().toDate();
	}

	public void setDueDate(Date aDate) {
		cta.setDueDate(new DateTime(aDate));
	}

	public Date getSnoozePeriod() {
		return cta.getSnoozeperiod().toDate();
	}

	public void setSnoozePeriod(Date date) {
		cta.setSnoozeperiod(new DateTime(date));
	}

	public Date getModifiedDate() {
		return cta.getModifiedDate().toDate();
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

	@PostConstruct
	public void init() {
		cta = new CallToAction();
		cta.setId(4722);

	}

	public boolean saveCTA() {
		DAOFactory factory = DAOFactory.getFactory();
		CallToActionDAO dao = factory.getCallToActionDAO();
		boolean inserted = dao.insertCTA(cta);

		return inserted;
	}

	public void deleteCTA() {
		DAOFactory factory = DAOFactory.getFactory();
		CallToActionDAO dao = factory.getCallToActionDAO();
		dao.deleteCTA(cta.getId());
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
