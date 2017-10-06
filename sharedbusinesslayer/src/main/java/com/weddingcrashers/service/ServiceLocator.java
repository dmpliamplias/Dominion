package com.weddingcrashers.service;

import com.weddingcrashers.db.H2Database;

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

    /** The logger. */
    private static final Logger LOG = Logger.getLogger(ServiceLocator.class.getSimpleName());

    /** The H2 database. */
    private static H2Database h2Database;


    // ---- Members

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
        h2Database = new H2Database();
        translator = new Translator("de");

        userService = new UserServiceImpl();
        highscoreService = new HighscoreServiceImpl();
        settingsService = new SettingsServiceImpl();
    }


    // ---- Methods

    public static void shutdownDatabase() {
        h2Database.shutdownDatabase();
    }

    public static Logger getLogger() {
        return LOG;
    }

    public UserService getUserService() {
        return userService;
    }

    public HighscoreService getHighscoreService() {
        return highscoreService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(String locale) {
        this.translator = new Translator(locale);
    }
    
}
