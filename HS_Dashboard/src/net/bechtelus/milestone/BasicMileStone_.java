package net.bechtelus.milestone;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.successStories.SuccessStories;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-07-05T17:37:31.611-0400")
@StaticMetamodel(BasicMileStone.class)
public class BasicMileStone_ {
	public static volatile SingularAttribute<BasicMileStone, Long> id;
	public static volatile SingularAttribute<BasicMileStone, String> description;
	public static volatile SingularAttribute<BasicMileStone, Boolean> template;
	public static volatile SingularAttribute<BasicMileStone, Calendar> startTime;
	public static volatile SingularAttribute<BasicMileStone, Calendar> endTime;
	public static volatile SingularAttribute<BasicMileStone, User> createby;
	public static volatile SingularAttribute<BasicMileStone, Calendar> createdDate;
	public static volatile SingularAttribute<BasicMileStone, String> note;
	public static volatile SingularAttribute<BasicMileStone, Calendar> modifiedDate;
	public static volatile SingularAttribute<BasicMileStone, User> modifiedby;
	public static volatile SingularAttribute<BasicMileStone, Integer> version;
	public static volatile SingularAttribute<BasicMileStone, SuccessStories> successStoryID;
	public static volatile SingularAttribute<BasicMileStone, String> name;
}
