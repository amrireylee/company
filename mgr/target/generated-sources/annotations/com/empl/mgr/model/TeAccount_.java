package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeAccount.class)
public abstract class TeAccount_ {

	public static volatile SingularAttribute<TeAccount, String> creator;
	public static volatile SingularAttribute<TeAccount, String> acctPassword;
	public static volatile SingularAttribute<TeAccount, Date> createTime;
	public static volatile SingularAttribute<TeAccount, Boolean> acctDeleteState;
	public static volatile SingularAttribute<TeAccount, Long> acctId;
	public static volatile SingularAttribute<TeAccount, String> acctNickname;
	public static volatile SingularAttribute<TeAccount, Integer> acctState;
	public static volatile SingularAttribute<TeAccount, String> acctName;
	public static volatile SingularAttribute<TeAccount, Boolean> acctSuper;
	public static volatile SingularAttribute<TeAccount, Date> timestamp;

}

