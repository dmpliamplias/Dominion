package com.weddingcrashers.service;

import com.weddingcrashers.model.Highscore;

import javax.persistence.EntityManager;
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
