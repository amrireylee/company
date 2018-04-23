package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeEmployeesTrainingLog.class)
public abstract class TeEmployeesTrainingLog_ {

	public static volatile SingularAttribute<TeEmployeesTrainingLog, Long> emplId;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, String> note;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, String> creator;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, Long> trainingItemId;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, Date> createTime;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, Long> id;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, Integer> state;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, String> applyTime;
	public static volatile SingularAttribute<TeEmployeesTrainingLog, Date> timestamp;

}

