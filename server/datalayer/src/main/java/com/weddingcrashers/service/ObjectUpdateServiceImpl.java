package com.weddingcrashers.service;

import javax.persistence.EntityManager;
import java.util.List;

import static com.weddingcrashers.util.EntityManagerFactory.getEntityManager;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link ObjectUpdateService}.
 *
 * @author dmpliamplias
 */
class ObjectUpdateServiceImpl extends BaseService implements ObjectUpdateService {

    // ---- Methods

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Object entity) {
        notNull(entity);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            em.persist(entity);
            commitTransaction(em);
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction(em);
        }
        finally {
            em.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object entity) {
        notNull(entity);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            em.merge(entity);
            commitTransaction(em);
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction(em);
        }
        finally {
            em.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(long id, Class clazz) {
        notNull(clazz);

        final EntityManager em = getEntityManager();
        try {
            final Object entity = em.find(clazz, id);
            em.remove(entity);
            commitTransaction(em);
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction(em);
        }
        finally {
            em.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public<T> List<T> list(Class clazz) {
        notNull(clazz);

        final EntityManager em = getEntityManager();
        return em.createQuery("from " + clazz.getSimpleName()).getResultList();
    }

    @Override
    protected Class getSubClass() {
        return ObjectUpdateServiceImpl.class;
    }

}
