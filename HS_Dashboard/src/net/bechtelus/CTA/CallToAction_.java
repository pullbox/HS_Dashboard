package net.bechtelus.CTA;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.user.User;
import org.joda.time.DateTime;

@Generated(value="Dali", date="2017-04-17T17:57:42.339-0400")
@StaticMetamodel(CallToAction.class)
public class CallToAction_ {
	public static volatile SingularAttribute<CallToAction, Long> id;
	public static volatile SingularAttribute<CallToAction, String> description;
	public static volatile SingularAttribute<CallToAction, User> assignee;
	public static volatile SingularAttribute<CallToAction, String> ctaType;
	public static volatile SingularAttribute<CallToAction, String> status;
	public static volatile SingularAttribute<CallToAction, String> priority;
	public static volatile SingularAttribute<CallToAction, String> reason;
	public static volatile SingularAttribute<CallToAction, String> snoozeReason;
	public static volatile SingularAttribute<CallToAction, DateTime> snoozeperiod;
	public static volatile SingularAttribute<CallToAction, String> ctaStatus;
	public static volatile SingularAttribute<CallToAction, String> source;
	public static volatile SingularAttribute<CallToAction, User> createby;
	public static volatile SingularAttribute<CallToAction, DateTime> createdDate;
	public static volatile SingularAttribute<CallToAction, Boolean> escalated;
	public static volatile SingularAttribute<CallToAction, DateTime> dueDate;
	public static volatile SingularAttribute<CallToAction, String> note;
	public static volatile SingularAttribute<CallToAction, DateTime> modifiedDate;
	public static volatile SingularAttribute<CallToAction, User> modifiedby;
}
