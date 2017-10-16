package com.weddingcrashers.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Logger;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;

/**
 * Translator.
 *
 * @author dmpliamplias
 */
public class Translator {

    // ---- Statics

    /** The supported locales. */
    private static final Locale[] availableLocales = {GERMAN, ENGLISH};

    /** The suffix for the translation files. */
    private static final String SUFFIX = ".properties";

    /** The path to translation resources. */
    private static final String PATH = "/translation/trans";

    /** The logger. */
    private static final Logger logger = ServiceLocator.getLogger();


    // ---- Members

    /** The current locale. */
    private Locale currentLocale;
    /** The translation properties. */
    private Properties translations;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param localeString the locale.
     */
    Translator(String localeString) {
        Locale locale = null;
        if (localeString != null) {
            for (Locale availableLocale : availableLocales) {
                String tmpLang = availableLocale.getLanguage();
                if (localeString.substring(0, tmpLang.length()).equals(tmpLang)) {
                    locale = availableLocale;
                    break;
                }
            }
        }

        // Load the resource strings
        translations = new Properties();

        boolean isDefault = false;
        InputStream in;
        if (locale == null) {
            // fallback properties
            in = getClass().getResourceAsStream(PATH + SUFFIX);
            isDefault = true;
        }
        else {
            // locale properties
            in = getClass().getResourceAsStream(PATH + "_" + locale + SUFFIX);
        }
        try {
            translations.load(in);
        } catch (IOException e) {
            logger.warning("Could not load translation");
        }
        if (isDefault) {
            locale = GERMAN;
        }
        Locale.setDefault(locale);
        currentLocale = locale;

        logger.info("Loaded resources for " + locale.getLanguage());
    }

    /**
     * @return the current locale.
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Returns the value for the given key from the translation properties file.
     *
     * @param key the key to load the value for.
     * @return the value for the given key.
     */
    public String getString(String key) {
        try {
            return translations.getProperty(key);
        } catch (MissingResourceException e) {
            logger.warning("Missing value for key: " + key);
            return "NOT DEFINED";
        }
    }
}
