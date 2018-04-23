package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TePosition.class)
public abstract class TePosition_ {

	public static volatile SingularAttribute<TePosition, Long> poDepartment;
	public static volatile SingularAttribute<TePosition, String> creator;
	public static volatile SingularAttribute<TePosition, String> poName;
	public static volatile SingularAttribute<TePosition, String> poDescription;
	public static volatile SingularAttribute<TePosition, Date> createTime;
	public static volatile SingularAttribute<TePosition, Long> poId;
	public static volatile SingularAttribute<TePosition, Date> timestamp;

}

