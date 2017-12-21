package com.weddingcrashers.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.weddingcrashers.model.BaseEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Boolean> isBlocked;
	public static volatile SingularAttribute<User, String> userEmail;
	public static volatile SingularAttribute<User, Boolean> isSuperUser;
	public static volatile SingularAttribute<User, String> userName;

}

