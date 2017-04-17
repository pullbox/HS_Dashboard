package net.bechtelus.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-17T17:54:06.604-0400")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> USER_TK;
	public static volatile SingularAttribute<User, String> USER_ID;
	public static volatile SingularAttribute<User, String> FULL_NAME;
	public static volatile SingularAttribute<User, Boolean> ACTIVE;
	public static volatile SingularAttribute<User, String> EMAIL;
	public static volatile SingularAttribute<User, String> TITLE;
}
