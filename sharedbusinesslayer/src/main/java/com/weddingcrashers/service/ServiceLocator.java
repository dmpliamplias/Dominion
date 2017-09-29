package com.weddingcrashers.service;

import com.weddingcrashers.service.ObjectUpdateService;
import com.weddingcrashers.service.ObjectUpdateServiceImpl;
import com.weddingcrashers.service.UserService;
import com.weddingcrashers.service.UserServiceImpl;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * The singleton instance of this class provide central storage for resources
 * used by the program. It also defines application-global constants, such as
 * the application name.
 * 
 * @author Brad Richards
 */
public class ServiceLocator {

    // ---- Statics

    /** The service locator singleton. */
    private static ServiceLocator serviceLocator;


    // ---- Members

    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    // Resources
    private Logger logger;
    private Translator translator;

    // Services
    private static ObjectUpdateService objectUpdateService;
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

}
