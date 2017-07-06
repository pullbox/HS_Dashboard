package net.bechtelus.successStories;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.account.Account;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-07-05T17:36:24.742-0400")
@StaticMetamodel(SuccessStories.class)
public class SuccessStories_ {
	public static volatile SingularAttribute<SuccessStories, Long> id;
	public static volatile SingularAttribute<SuccessStories, String> description;
	public static volatile SingularAttribute<SuccessStories, Boolean> template;
	public static volatile SingularAttribute<SuccessStories, Account> account;
	public static volatile SingularAttribute<SuccessStories, User> createby;
	public static volatile SingularAttribute<SuccessStories, Calendar> createdDate;
	public static volatile SingularAttribute<SuccessStories, Calendar> modifiedDate;
	public static volatile SingularAttribute<SuccessStories, User> modifiedby;
	public static volatile SingularAttribute<SuccessStories, Integer> version;
	public static volatile SingularAttribute<SuccessStories, String> name;
}
