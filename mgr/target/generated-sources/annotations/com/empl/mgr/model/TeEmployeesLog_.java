package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeEmployeesLog.class)
public abstract class TeEmployeesLog_ {

	public static volatile SingularAttribute<TeEmployeesLog, Long> emplId;
	public static volatile SingularAttribute<TeEmployeesLog, String> note;
	public static volatile SingularAttribute<TeEmployeesLog, String> creator;
	public static volatile SingularAttribute<TeEmployeesLog, Date> createTime;
	public static volatile SingularAttribute<TeEmployeesLog, Long> id;
	public static volatile SingularAttribute<TeEmployeesLog, Integer> type;
	public static volatile SingularAttribute<TeEmployeesLog, Date> timestamp;

}

