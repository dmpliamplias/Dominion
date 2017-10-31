package com.weddingcrashers.service;

import com.weddingcrashers.db.H2Database;
import com.weddingcrashers.service.Translator.Language;

import java.util.logging.Logger;

import static com.weddingcrashers.service.Translator.Language.GERMAN;

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


    // ---- Members

    // TODO: 31.10.17 dmpliamplias maybe conf for default language or last played language.
    /** The H2 database. */
    private H2Database h2Database;

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
        translator = new Translator(GERMAN);

        userService = new UserServiceImpl();
        highscoreService = new HighscoreServiceImpl();
        settingsService = new SettingsServiceImpl();
    }


    // ---- Methods

    public static Logger getLogger() {
        return LOG;
    }

    public void startDatabase() {
        h2Database.establishConnection();
    }

    public void shutdownDatabase() {
        h2Database.shutdownDatabase();
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

    public void setTranslator(Language language) {
        this.translator = new Translator(language);
    }
    
}
