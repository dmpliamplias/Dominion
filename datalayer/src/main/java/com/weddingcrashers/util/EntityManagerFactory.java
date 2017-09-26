package com.weddingcrashers.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static com.weddingcrashers.util.DatabaseStatics.UNIT_NAME;

/**
 * The entity manager util class. It provides an entity
 * manager to perform database transactions.
 *
 * @author dmpliamplias
 */
public final class EntityManagerFactory {

    // ---- Members

    /** The database name. */
    private static final String DATABASE_NAME = "weddingcrashers_dominion";

    /** The entity manager factory. */
    private static final javax.persistence.EntityManagerFactory entityManagerFactory;


    // ---- Constructor

    /** Static block which gets performed after class is initialized. */
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    // ---- Methods

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }

}