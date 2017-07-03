package net.bechtelus.milestone;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-07-03T14:18:24.423-0400")
@StaticMetamodel(MileStone.class)
public class MileStone_ {
	public static volatile SingularAttribute<MileStone, Long> id;
	public static volatile SingularAttribute<MileStone, String> description;
	public static volatile SingularAttribute<MileStone, User> createby;
	public static volatile SingularAttribute<MileStone, Calendar> createdDate;
	public static volatile SingularAttribute<MileStone, Calendar> modifiedDate;
	public static volatile SingularAttribute<MileStone, User> modifiedby;
	public static volatile SingularAttribute<MileStone, Integer> version;
	public static volatile SingularAttribute<MileStone, String> note;
}
