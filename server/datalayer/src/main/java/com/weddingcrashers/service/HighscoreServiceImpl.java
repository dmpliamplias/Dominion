package com.weddingcrashers.service;

import com.weddingcrashers.model.Highscore;
import org.apache.commons.lang3.Validate;

import javax.persistence.EntityManager;
import java.util.List;

import static com.weddingcrashers.util.EntityManagerFactory.getEntityManager;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link HighscoreService}.
 *
 * @author dmpliamplias
 */
public class HighscoreServiceImpl extends BaseService implements HighscoreService {

    // ---- Members

    /** The object update service. */
    private final ObjectUpdateService objectUpdateService = new ObjectUpdateServiceImpl();


    // ---- Methods

    @Override
    public void saveHighscore(final Highscore highscore) {
        notNull(highscore);

        final EntityManager em = getEntityManager();
        try {
            startTransaction(em);
            objectUpdateService.create(highscore);
            commitTransaction(em);
        }
        catch (Exception e) {
            rollbackTransaction(em);
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Highscore> getHighscores() {
        // TODO: 30.09.2017 top 10
        final List<Highscore> highscores = objectUpdateService.list(Highscore.class);
        return highscores;
    }

    @Override
    protected Class getSubClass() {
        return HighscoreServiceImpl.class;
    }

}
