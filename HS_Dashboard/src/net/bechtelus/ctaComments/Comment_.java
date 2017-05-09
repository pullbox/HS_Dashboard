package net.bechtelus.ctaComments;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.CTA.CallToAction;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-05-09T16:13:41.342-0400")
@StaticMetamodel(Comment.class)
public class Comment_ {
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, CallToAction> cta_id;
	public static volatile SingularAttribute<Comment, User> owner_id;
	public static volatile SingularAttribute<Comment, User> createby;
	public static volatile SingularAttribute<Comment, Calendar> createdDate;
	public static volatile SingularAttribute<Comment, Calendar> modifiedDate;
	public static volatile SingularAttribute<Comment, User> modifiedby;
	public static volatile SingularAttribute<Comment, Integer> version;
	public static volatile SingularAttribute<Comment, String> comment;
}
