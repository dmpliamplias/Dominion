package com.weddingcrashers.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Highscore.class)
public abstract class Highscore_ {

	public static volatile SingularAttribute<Highscore, Date> dateOfHighscore;
	public static volatile SingularAttribute<Highscore, Integer> durationForHighscore;
	public static volatile SingularAttribute<Highscore, Long> id;
	public static volatile SingularAttribute<Highscore, Long> userid;
	public static volatile SingularAttribute<Highscore, Integer> points;

}

