package com.empl.mgr.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeAddress.class)
public abstract class TeAddress_ {

	public static volatile SingularAttribute<TeAddress, Long> adsVillage;
	public static volatile SingularAttribute<TeAddress, Long> adsProvince;
	public static volatile SingularAttribute<TeAddress, String> adsDetailed;
	public static volatile SingularAttribute<TeAddress, Integer> adsType;
	public static volatile SingularAttribute<TeAddress, Long> adsId;
	public static volatile SingularAttribute<TeAddress, Long> adsCounty;
	public static volatile SingularAttribute<TeAddress, Long> adsCity;
	public static volatile SingularAttribute<TeAddress, Long> adsTownship;
	public static volatile SingularAttribute<TeAddress, Date> timestamp;

}

