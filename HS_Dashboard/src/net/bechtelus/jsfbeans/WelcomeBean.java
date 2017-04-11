package net.bechtelus.jsfbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import net.bechtelus.CTA.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.inject.Named;





@Named("jsfShowCTAs")
@SessionScoped
public class WelcomeBean implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private List<CallToAction> ctas;
	private static MSSQL
	
	
	@PostConstruct
	public void init() {
		
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ctas = null;
		ctas = new ArrayList<CallToAction>();
		
		for (Ticket ticket : zd.getTicketsFromSearch(this.searchTerm)) {
			// System.out.println("Body: " + comment.getBody());
			tickets.add(ticketextended);

		}
		
	}

	@PreDestroy
	public void close() {
		zd.close();
		log("destroyed");
	}

	public TicketExtended getRowData(String arg0) {
		log("getRowData " + arg0);
		return null;
	}

	public Object getRowKey(TicketExtended arg0) {

		log("getRowkey " + arg0);
		return null;
	}

	private void log(String a) {
		System.out.println(getClass().getName() + " " + a);

	}

	private String time() {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		return timestamp;
	}

}
