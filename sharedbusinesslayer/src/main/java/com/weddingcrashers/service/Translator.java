package com.weddingcrashers.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Logger;

public class Translator {

    // ---- Statics

    /** The supported locales. */
    private static final Locale[] availableLocales = {Locale.GERMAN, Locale.ENGLISH};

    /** The suffix for the translation files. */
    private static final String SUFFIX = ".properties";

    /** The logger. */
    private static final Logger logger = ServiceLocator.getLogger();


    // ---- Members

    /** The current locale. */
    private Locale currentLocale;
    /** The translation properties. */
    private Properties translations;


    // ---- Constructor

    /**
     * Constructor
     *
     * @param localeString the locale.
     */
    public Translator(String localeString) {
        // Can we find the language in our supported locales?
        // If not, use VM default locale
        Locale locale = Locale.getDefault();
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
        final InputStream in = getClass().getResourceAsStream("/translation/trans_" + locale + SUFFIX);
        try {
            translations.load(in);
        } catch (IOException e) {
            logger.warning("Could not load translation");
        }
        Locale.setDefault(locale); // Change VM default (for dialogs, etc.)
        currentLocale = locale;

        logger.info("Loaded resources for " + locale.getLanguage());
    }

    /**
     * Return the current locale; this is useful for formatters, etc.
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Public method to get string resources, default to "--" *
     */
    public String getString(String key) {
        try {
            return translations.getProperty(key);
        } catch (MissingResourceException e) {
            logger.warning("Missing string: " + key);
            return "--";
        }
    }
}
