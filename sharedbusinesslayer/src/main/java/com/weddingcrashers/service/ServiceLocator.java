package com.weddingcrashers.service;

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

    /** The object update service. */
    private static ObjectUpdateService objectUpdateService;
    /** The user service. */
    private static UserService userService;
    /** The highscore service. */
    private static HighscoreService highscoreService;
    /** The settings service. */
    private static SettingsService settingsService;


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
        objectUpdateService = new ObjectUpdateServiceImpl();
        userService = new UserServiceImpl();
        highscoreService = new HighscoreServiceImpl();
        settingsService = new SettingsServiceImpl();
    }


    // ---- Methods


    public static ObjectUpdateService getObjectUpdateService() {
        return objectUpdateService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static HighscoreService getHighscoreService() {
        return highscoreService;
    }

    public static SettingsService getSettingsService() {
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
