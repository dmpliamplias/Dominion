package com.weddingcrashers.service;

import com.weddingcrashers.clientconnectionutils.ServerConnectionService;

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
    /** The server connection service. */
    private ServerConnectionService serverConnectionService;
    /** The object update service. */
    private static ObjectUpdateService objectUpdateService;
    /** The user service. */
    private static UserService userService;


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
    }


    // ---- Methods


    public static ObjectUpdateService getObjectUpdateService() {
        return objectUpdateService;
    }

    public static UserService getUserService() {
        return userService;
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

    public ServerConnectionService getServerConnectionService() {
        return serverConnectionService;
    }

    public void setServerConnectionService(ServerConnectionService serverConnectionService) {
        this.serverConnectionService = serverConnectionService;
    }


}
