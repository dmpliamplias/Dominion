package com.weddingcrashers.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Logger;

public class Translator {

    // ---- Members

    /** The service locator. */
    private ServiceLocator sl = ServiceLocator.getServiceLocator();
    /** The logger. */
    private Logger logger = sl.getLogger();
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
            Locale[] availableLocales = sl.getLocales();
            for (int i = 0; i < availableLocales.length; i++) {
                String tmpLang = availableLocales[i].getLanguage();
                if (localeString.substring(0, tmpLang.length()).equals(tmpLang)) {
                    locale = availableLocales[i];
                    break;
                }
            }
        }

        // Load the resource strings
        translations = new Properties();
        final InputStream in = getClass().getResourceAsStream("/translation/trans_" + locale);
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
