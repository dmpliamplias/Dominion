package com.weddingcrashers.service;

import com.weddingcrashers.model.Highscore;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static com.weddingcrashers.db.EntityManagerFactory.getEntityManager;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link HighscoreService}.
 *
 * @author dmpliamplias
 */
public class HighscoreServiceImpl extends BaseService implements HighscoreService {

    // ---- Members

    /** The object update service. */
    @SuppressWarnings("unchecked")
    private final ObjectUpdateService<Highscore> objectUpdateService = new ObjectUpdateServiceImpl();


    // ---- Methods

    @Override
    public Highscore saveHighscore(Highscore highscore) {
        notNull(highscore);

        return objectUpdateService.create(highscore);
    }

    @Override
    public List<Highscore> getAllHighscores() {
        return objectUpdateService.list(Highscore.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Highscore> getHighscores(int results) {
        final EntityManager em = getEntityManager();
        final Query query = em.createQuery("from " + Highscore.class.getSimpleName());

        query.setMaxResults(results);
        return query.getResultList();
    }

    @Override
    protected Class getSubClass() {
        return HighscoreServiceImpl.class;
    }

}
