package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeDepartment.class)
public abstract class TeDepartment_ {

	public static volatile SingularAttribute<TeDepartment, String> deptDescription;
	public static volatile SingularAttribute<TeDepartment, String> deptName;
	public static volatile SingularAttribute<TeDepartment, String> creator;
	public static volatile SingularAttribute<TeDepartment, Long> deptPrincipal;
	public static volatile SingularAttribute<TeDepartment, Date> createTime;
	public static volatile SingularAttribute<TeDepartment, Long> deptId;
	public static volatile SingularAttribute<TeDepartment, Date> timestamp;

}

