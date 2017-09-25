package com.weddingcrashers.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

import static com.weddingcrashers.util.EntityManagerFactory.getEntityManager;

public class ObjectUpdateServiceImpl implements ObjectUpdateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Object entity) {
        final EntityManager em = getEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            em.flush();
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object entity) {
        final EntityManager em = getEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
            em.flush();
            tx.commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(long id, Class clazz) {
        final EntityManager em = getEntityManager();
        final EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            final Object entity = em.find(clazz, id);
            em.remove(entity);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public<T> List<T> list(Class clazz) {
        final EntityManager em = getEntityManager();
        List<T> entities = new ArrayList<>();
        try {
            em.getTransaction().begin();
            entities = em.createQuery("from " + clazz.getSimpleName()).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return entities;
    }

}
