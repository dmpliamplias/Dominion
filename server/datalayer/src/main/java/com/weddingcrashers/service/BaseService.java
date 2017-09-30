package com.weddingcrashers.service;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/**
 * Base service.
 *
 * @author dmpliamplias
 */
public abstract class BaseService {

    // ---- Members

    /** The logger. */
    protected static Logger LOG;


    // ---- Methods

    /**
     * Returns the subclass name.
     *
     * @return the name of the subclass.
     */
    protected abstract Class getSubClass();

    /**
     * Returns the logger for the corresponding subclass.
     *
     * @return the logger for the corresponding subclass.
     */
    protected Logger getServiceLogger() {
        if (LOG == null) {
            LOG = Logger.getLogger(getSubClass().getSimpleName());
        }
        return LOG;
    }

    /**
     * Starts the database transaction.
     *
     * @param em the entity manager.
     */
    protected void startTransaction(final EntityManager em) {
        em.getTransaction().begin();
    }

    /**
     * Commits the database transaction.
     *
     * @param em the entity manager.
     */
    protected void commitTransaction(final EntityManager em) {
        em.getTransaction().commit();
        em.flush();
    }

    /**
     * Rollback the database transaction.
     *
     * @param em the entity manager.
     */
    protected void rollbackTransaction(final EntityManager em) {
        em.getTransaction().rollback();
    }

    /**
     * Logs the given exception.
     *
     * @param logger the logger.
     * @param e the exception which occured.
     */
    protected void logException(Logger logger, Exception e) {
        // TODO: 30.09.2017 check instance of the exception and log which and what gone wrong
        logger.log(SEVERE, e.getMessage());
    }

}
