package com.weddingcrashers.service;

import com.weddingcrashers.db.H2Database;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Service locator.
 *
 * @author dmpliamplias
 */
public class ServiceLocator {

    // ---- Statics

    /** The service locator singleton. */
    private static ServiceLocator serviceLocator;


    // ---- Members

    /** The locales. */
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    /** The logger. */
    private Logger logger;
    /** The translator. */
    private Translator translator;

    /** The user service. */
    private final UserService userService;
    /** The highscore service. */
    private final HighscoreService highscoreService;
    /** The settings service. */
    private final SettingsService settingsService;


    // ---- Factory methods

    /**
     * Returns {@link ServiceLocator} instance (singleton).
     *
     * @return The singleton resource locator
     */
    public static ServiceLocator getServiceLocator() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
        return serviceLocator;
    }


    // ---- Constructor

    /**
     * Constructor.
     */
    private ServiceLocator() {
        new H2Database();
        userService = new UserServiceImpl();
        highscoreService = new HighscoreServiceImpl();
        settingsService = new SettingsServiceImpl();
    }


    // ---- Methods

    public UserService getUserService() {
        return userService;
    }

    public HighscoreService getHighscoreService() {
        return highscoreService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Locale[] getLocales() {
        return locales;
    }

    public Translator getTranslator() {
        return translator;
    }
    
    public void setTranslator(Translator translator) {
        this.translator = translator;
    }



}
