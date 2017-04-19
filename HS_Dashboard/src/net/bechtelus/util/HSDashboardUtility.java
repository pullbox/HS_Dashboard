package net.bechtelus.util;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HSDashboardUtility implements Serializable {

	public static final String MSSQL_DB_PROPERRTIES_FILE = "mssql-db.properties";
	public static final String DB_PROPERRTIES_FILE = "db.properties";
	// public static final String ORDER_TYPE_BUY = "buy";
	// public static final String ORDER_TYPE_SELL = "sell";
	// public static final String ORDER_TYPE_SELL_ENHANCED = "sellEnhanced";
	// public static final String ORDER_STATUS_CLOSED = "completed";
	// public static final String ORDER_STATUS_OPEN = "open";

	// public static final int MAX_QUERY_TOP_ORDERS = 5;
	// public static final int MAX_QUERY_ORDERS = 5;

	// public static final BigDecimal PENNY_STOCK_P = BigDecimal.valueOf(0.1);
	// public static final BigDecimal JUNK_STOCK_MIRACLE_MULTIPLIER = BigDecimal
	// .valueOf(500);
	// public static final BigDecimal STOCK_P_HIGH_BAR =
	// BigDecimal.valueOf(1000);
	// public static final BigDecimal STOCK_P_HIGH_BAR_CRASH = BigDecimal
	// .valueOf(0.05);
	// public static final BigDecimal STOCK_CHANGE_MAX_PERCENT = BigDecimal
	// .valueOf(5);
	// public static final BigDecimal BUY_FEE = BigDecimal.valueOf(15.95);
	// public static final BigDecimal SELL_FEE = BigDecimal.valueOf(25.95);

	private static final Log logger = LogFactory.getLog(HSDashboardUtility.class);

	protected static EntityManagerFactory emf;

	public static Date convertToSqlDate(Calendar calendar) {
		return new Date(calendar.getTimeInMillis());
	}

	public static Calendar convertToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}

	public static EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("HS_Dashboard", new java.util.HashMap());
		}
		return emf;
	}
}
