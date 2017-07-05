package net.bechtelus.csmstones;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.account.Account;
import net.bechtelus.milestone.BasicMileStone;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-07-03T16:33:12.191-0400")
@StaticMetamodel(SuccessStories.class)
public class SuccessStories_ {
	public static volatile SingularAttribute<SuccessStories, Long> id;
	public static volatile SingularAttribute<SuccessStories, String> description;
	public static volatile SingularAttribute<SuccessStories, Boolean> template;
	public static volatile SingularAttribute<SuccessStories, BasicMileStone> milestone;
	public static volatile SingularAttribute<SuccessStories, Calendar> startTime;
	public static volatile SingularAttribute<SuccessStories, Calendar> endTime;
	public static volatile SingularAttribute<SuccessStories, User> createby;
	public static volatile SingularAttribute<SuccessStories, Calendar> createdDate;
	public static volatile SingularAttribute<SuccessStories, Calendar> modifiedDate;
	public static volatile SingularAttribute<SuccessStories, User> modifiedby;
	public static volatile SingularAttribute<SuccessStories, Integer> version;
	public static volatile SingularAttribute<SuccessStories, Account> account;
}
