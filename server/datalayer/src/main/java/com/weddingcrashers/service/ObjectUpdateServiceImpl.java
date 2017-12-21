package com.weddingcrashers.service;

import com.weddingcrashers.model.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;

import static com.weddingcrashers.db.EntityManagerFactory.getEntityManager;
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
    public BaseEntity create(BaseEntity entity) {
        notNull(entity);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            em.persist(entity);
            commitTransaction();
            entity = em.find(entity.getClass(), entity.getId());
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction();
        }
        finally {
            releaseResources();
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseEntity update(BaseEntity entity) {
        notNull(entity);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            em.merge(entity);
            commitTransaction();
            entity = em.find(entity.getClass(), entity.getId());
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction();
        }
        finally {
            releaseResources();
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean delete(BaseEntity entity) {
        notNull(entity);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            entity = em.find(entity.getClass(), entity.getId());
//            em.remove(entity);
            entity.setDeleted(true);
            commitTransaction();
        }
        catch (Exception e) {
            logException(getServiceLogger(), e);
            rollbackTransaction();
        }
        finally {
            releaseResources();
        }
        return entity.isDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BaseEntity> list(Class clazz, boolean showInactive) {
        notNull(clazz);

        final EntityManager em = getEntityManager();
        final StringBuilder sb = new StringBuilder();
        sb.append("from ").append(clazz.getSimpleName());
        if (!showInactive) {
            sb.append(" where deleted = false");
        }
        final String query = sb.toString();
        return em.createQuery(query).getResultList();
    }

    @Override
    protected Class getSubClass() {
        return ObjectUpdateServiceImpl.class;
    }

}
