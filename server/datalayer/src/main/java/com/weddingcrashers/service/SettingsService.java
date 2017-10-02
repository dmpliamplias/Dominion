package com.weddingcrashers.service;

import com.weddingcrashers.model.Settings;
import com.weddingcrashers.model.User;

/**
 * Settings service.
 *
 * @author dmpliamplias
 */
public interface SettingsService {

    /**
     * Saves the settings for the given user.
     *
     * @param settings the settings to save.
     */
    Settings create(Settings settings);

    /**
     * Loads the settings for the given user.
     *
     * @param user the user to load settings for.
     */
    Settings loadSettingsFor(User user);

}
