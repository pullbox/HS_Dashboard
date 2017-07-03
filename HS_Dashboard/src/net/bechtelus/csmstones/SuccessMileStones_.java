package net.bechtelus.csmstones;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.bechtelus.milestone.BasicMileStone;
import net.bechtelus.user.User;

@Generated(value="Dali", date="2017-07-03T15:25:07.987-0400")
@StaticMetamodel(SuccessMileStones.class)
public class SuccessMileStones_ {
	public static volatile SingularAttribute<SuccessMileStones, Long> id;
	public static volatile SingularAttribute<SuccessMileStones, String> description;
	public static volatile SingularAttribute<SuccessMileStones, Calendar> startTime;
	public static volatile SingularAttribute<SuccessMileStones, Calendar> endTime;
	public static volatile SingularAttribute<SuccessMileStones, User> createby;
	public static volatile SingularAttribute<SuccessMileStones, Calendar> createdDate;
	public static volatile SingularAttribute<SuccessMileStones, Calendar> modifiedDate;
	public static volatile SingularAttribute<SuccessMileStones, User> modifiedby;
	public static volatile SingularAttribute<SuccessMileStones, Integer> version;
	public static volatile SingularAttribute<SuccessMileStones, BasicMileStone> milestone;
}
