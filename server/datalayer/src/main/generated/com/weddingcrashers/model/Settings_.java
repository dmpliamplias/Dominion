package com.weddingcrashers.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Settings.class)
public abstract class Settings_ extends com.weddingcrashers.model.BaseEntity_ {

	public static volatile SingularAttribute<Settings, String> settings;
	public static volatile SingularAttribute<Settings, Boolean> sound;
	public static volatile SingularAttribute<Settings, User> user;

}
