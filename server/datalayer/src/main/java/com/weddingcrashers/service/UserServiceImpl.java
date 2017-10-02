package com.weddingcrashers.service;

import com.weddingcrashers.db.EntityManagerFactory;
import com.weddingcrashers.model.User;
import com.weddingcrashers.model.User_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link UserService}.
 *
 * @author dmpliamplias.
 */
public class UserServiceImpl extends BaseService implements UserService {

    // ---- Members

    /** The object update service. */
    @SuppressWarnings("unchecked")
    private final ObjectUpdateService<User> objectUpdateService = new ObjectUpdateServiceImpl();


    // ---- Methods

    @Override
    public User create(final User user) {
        notNull(user);
        return objectUpdateService.create(user);
    }

    @Override
    public User update(User user) {
        notNull(user);
        return objectUpdateService.update(user);
    }

    @Override
    public boolean delete(User user) {
        notNull(user);
        return objectUpdateService.delete(user);
    }

    @Override
    public User getUserByEmail(final String email) {
        notNull(email);

        // get em and cb builder
        final EntityManager em = EntityManagerFactory.getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        // define root and query
        final CriteriaQuery<User> query = cb.createQuery(User.class);
        final Root<User> from = query.from(User.class);

        // query select root
        query.select(from);
        // where clause
        query.where(cb.equal(from.get(User_.userEmail), email));

        // execute query
        final TypedQuery<User> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<User> list() {
        return objectUpdateService.list(User.class);
    }

    @Override
    protected Class getSubClass() {
        return UserServiceImpl.class;
    }

}
