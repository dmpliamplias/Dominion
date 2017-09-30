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
    void saveHighscore(Highscore highscore);

    /**
     * Gets the 10 highest scores.
     *
     * @return the 10 highest scores.
     */
    List<Highscore> getHighscores();

}
