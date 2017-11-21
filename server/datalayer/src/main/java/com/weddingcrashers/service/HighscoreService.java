package com.weddingcrashers.service;

import com.weddingcrashers.model.Highscore;

import java.util.List;

/**
 * Highscore service.
 *
 * @author dmpliamplias
 */
public interface HighscoreService {

    /**
     * Saves the given highscore.
     *
     * @param highscore the highscore to save.
     */
    Highscore saveHighscore(Highscore highscore);

    /**
     * Gets all highscores.
     *
     * @return all highscores.
     */
    List<Highscore> getAllHighscores();

    /**
     * Gets the highscore for the given amount.
     *
     * @param results the amount of the results to query.
     * @return the limited highscores.
     */
    List<Highscore> getHighscores(int results);

}
