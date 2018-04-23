package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeTrainingNote.class)
public abstract class TeTrainingNote_ {

	public static volatile SingularAttribute<TeTrainingNote, String> note;
	public static volatile SingularAttribute<TeTrainingNote, String> creator;
	public static volatile SingularAttribute<TeTrainingNote, Long> trainingId;
	public static volatile SingularAttribute<TeTrainingNote, Date> createTime;
	public static volatile SingularAttribute<TeTrainingNote, Long> id;
	public static volatile SingularAttribute<TeTrainingNote, Integer> state;
	public static volatile SingularAttribute<TeTrainingNote, Date> timestamp;

}

