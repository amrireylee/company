package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeTraining.class)
public abstract class TeTraining_ {

	public static volatile SingularAttribute<TeTraining, Integer> number;
	public static volatile SingularAttribute<TeTraining, String> creator;
	public static volatile SingularAttribute<TeTraining, Date> createTime;
	public static volatile SingularAttribute<TeTraining, Boolean> isInsertAttend;
	public static volatile SingularAttribute<TeTraining, String> name;
	public static volatile SingularAttribute<TeTraining, String> description;
	public static volatile SingularAttribute<TeTraining, String> startTime;
	public static volatile SingularAttribute<TeTraining, Long> id;
	public static volatile SingularAttribute<TeTraining, String> endTime;
	public static volatile SingularAttribute<TeTraining, Integer> state;
	public static volatile SingularAttribute<TeTraining, Date> timestamp;

}

