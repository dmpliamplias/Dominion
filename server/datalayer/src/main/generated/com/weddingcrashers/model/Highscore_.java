package com.weddingcrashers.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Highscore.class)
public abstract class Highscore_ extends com.weddingcrashers.model.BaseEntity_ {

	public static volatile SingularAttribute<Highscore, LocalDateTime> dateOfHighscore;
	public static volatile SingularAttribute<Highscore, Integer> durationForHighscore;
	public static volatile SingularAttribute<Highscore, User> user;
	public static volatile SingularAttribute<Highscore, Integer> points;

}

