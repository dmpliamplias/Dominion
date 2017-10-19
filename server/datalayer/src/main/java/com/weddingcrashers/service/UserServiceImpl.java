package com.weddingcrashers.service;

import com.weddingcrashers.db.EntityManagerFactory;
import com.weddingcrashers.model.User;
import com.weddingcrashers.model.User_;
import com.weddingcrashers.util.datalayer.SecurityUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
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
        final User userByEmail = getUserByEmail(user.getUserEmail());
        if (userByEmail == null) {
            final String hashedPassword = SecurityUtils.generatePBKDF2WithHMACSHA1Password(user.getPassword());
            user.setPassword(hashedPassword);
            return objectUpdateService.create(user);
        }
        return userByEmail;
    }

    @Override
    public User update(User user) {
        notNull(user);

        final EntityManager em = EntityManagerFactory.getEntityManager();
        final User contextUser = em.find(user.getClass(), user.getId());
        contextUser.setUserName(user.getUserName());
        contextUser.setUserEmail(user.getUserEmail());
        contextUser.setPassword(SecurityUtils.generatePBKDF2WithHMACSHA1Password(user.getPassword()));
//        contextUser.setIsBlocked(user.getIsBlocked());
//        contextUser.setIsSuperUser(user.getIsSuperUser());

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
        CriteriaQuery<User> query = cb.createQuery(User.class);
        final Root<User> from = query.from(User.class);

        // query select root
        query.select(from);
        // where clause
        query = getUserByQuery(from, cb, query, User_.userEmail, email);

        // execute query
        final TypedQuery<User> typedQuery = em.createQuery(query);
        try {
            typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            // if not result exists return null
            return null;
        }
        return typedQuery.getSingleResult();
    }

    public String getPasswordFor(User user) {
        notNull(user);

        final EntityManager entityManager = EntityManagerFactory.getEntityManager();
        final User foundUser = entityManager.find(user.getClass(), user.getId());
        return foundUser.getPassword();
    }

    @Override
    public List<User> list() {
        return objectUpdateService.list(User.class);
    }

    @Override
    protected Class getSubClass() {
        return UserServiceImpl.class;
    }

    /**
     * Returns the query with the given specification.
     *
     * @param from the root of the query.
     * @param cb the criteria builder.
     * @param query the query.
     * @param attribute the attribute to check.
     * @param value the value to check.
     * @return the query for the given specification.
     */
    private CriteriaQuery<User> getUserByQuery(Root<User> from, CriteriaBuilder cb, CriteriaQuery<User> query, SingularAttribute<User, String> attribute, String value) {
        query.where(cb.equal(from.get(attribute), value));
        return query;
    }

}
