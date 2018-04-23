package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeRole.class)
public abstract class TeRole_ {

	public static volatile SingularAttribute<TeRole, String> creator;
	public static volatile SingularAttribute<TeRole, Date> createTime;
	public static volatile SingularAttribute<TeRole, Long> roleId;
	public static volatile SingularAttribute<TeRole, String> roleName;
	public static volatile SingularAttribute<TeRole, String> roleLabel;
	public static volatile SingularAttribute<TeRole, String> roleDescription;
	public static volatile SingularAttribute<TeRole, Date> timestamp;

}

