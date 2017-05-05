package net.bechtelus.account;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-05-05T16:38:53.813-0400")
@StaticMetamodel(Account.class)
public class Account_ {
	public static volatile SingularAttribute<Account, Long> ACCOUNT_TK;
	public static volatile SingularAttribute<Account, String> ACCOUNT_ID;
	public static volatile SingularAttribute<Account, String> ACCOUNT_NAME;
	public static volatile SingularAttribute<Account, String> ACCOUNT_TYPE;
	public static volatile SingularAttribute<Account, User> CUSTOMER_SUCCESS_MANAGER;
	public static volatile SingularAttribute<Account, User> ENTERPRISE_ARCHITECT_ASSIGNED;
	public static volatile SingularAttribute<Account, Date> SUB_END_DATE;
	public static volatile SingularAttribute<Account, Integer> ZENDESK_ORG_ID;
	public static volatile SingularAttribute<Account, Boolean> ACTIVE_SUBS_CUSTOMER;
	public static volatile SingularAttribute<Account, User> TECHNICAL_ACCOUNT_MANAGER;
	public static volatile SingularAttribute<Account, String> CUSTOMER_TYPE;
	public static volatile SingularAttribute<Account, User> OWNER_ID;
	public static volatile SingularAttribute<Account, Boolean> HDS_CUSTOMER;
}
