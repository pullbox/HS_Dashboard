package net.bechtelus.CTA;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-05-01T16:43:12.440-0400")
@StaticMetamodel(CallToAction.class)
public class CallToAction_ {
	public static volatile SingularAttribute<CallToAction, Long> id;
	public static volatile SingularAttribute<CallToAction, String> description;
	public static volatile SingularAttribute<CallToAction, User> assignee;
	public static volatile SingularAttribute<CallToAction, String> type;
	public static volatile SingularAttribute<CallToAction, String> status;
	public static volatile SingularAttribute<CallToAction, String> priority;
	public static volatile SingularAttribute<CallToAction, String> reason;
	public static volatile SingularAttribute<CallToAction, String> snoozeReason;
	public static volatile SingularAttribute<CallToAction, Calendar> snoozeperiod;
	public static volatile SingularAttribute<CallToAction, String> source;
	public static volatile SingularAttribute<CallToAction, User> createby;
	public static volatile SingularAttribute<CallToAction, Calendar> createdDate;
	public static volatile SingularAttribute<CallToAction, Boolean> escalated;
	public static volatile SingularAttribute<CallToAction, Calendar> dueDate;
	public static volatile SingularAttribute<CallToAction, String> note;
	public static volatile SingularAttribute<CallToAction, Calendar> modifiedDate;
	public static volatile SingularAttribute<CallToAction, User> modifiedby;
	public static volatile SingularAttribute<CallToAction, Integer> version;
	public static volatile SingularAttribute<CallToAction, Integer> impact;
}
