package com.weddingcrashers.service;

import com.weddingcrashers.model.User;
import com.weddingcrashers.model.User_;
import com.weddingcrashers.util.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link UserService}.
 *
 * @author dmpliamplias.
 */
public class UserServiceImpl implements UserService {

    // ---- Members

    /** The object update service. */
    private final ObjectUpdateService objectUpdateService = new ObjectUpdateServiceImpl();


    // ---- Methods

    @Override
    public void create(final User user) {
        notNull(user);

        objectUpdateService.create(user);
    }

    @Override
    public User getUserByEmail(final String email) {
        notNull(email);

        final EntityManager em = EntityManagerFactory.getEntityManager();
        final EntityTransaction tx = em.getTransaction();
        User user = null;
        try {
            tx.begin();
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<User> query = cb.createQuery(User.class);
            final Root<User> from = query.from(User.class);
            query.select(from);
            query.where(cb.equal(from.get(User_.userEmail), email));
            final TypedQuery<User> typedQuery = em.createQuery(query);
            user = typedQuery.getSingleResult();
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        return user;
    }

}
