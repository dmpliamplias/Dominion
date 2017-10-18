package com.weddingcrashers.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Logger;

import static com.weddingcrashers.service.Translator.Language.ENGLISH;
import static com.weddingcrashers.service.Translator.Language.GERMAN;
import static com.weddingcrashers.service.Translator.Language.SWISS_GERMAN;

/**
 * Translator.
 *
 * @author dmpliamplias
 */
public class Translator {

    /**
     * Enum for language.
     */
    public enum Language {
        /** German. */
        GERMAN,
        /** Swiss german. */
        SWISS_GERMAN,
        /** English. */
        ENGLISH
    }


    // ---- Statics

    /** The suffix for the translation files. */
    private static final String SUFFIX = ".properties";

    /** The path to translation resources. */
    private static final String PATH = "/translation/trans";

    /** The logger. */
    private static final Logger logger = ServiceLocator.getLogger();


    // ---- Members

    /** The translation properties. */
    private Properties translations;
    /** The current language. */
    private Language currentLanguage;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param language the language.
     */
    Translator(Language language) {
        translations = new Properties();

        String languageAbbrev = null;
        switch (language) {
            case GERMAN:
                currentLanguage = GERMAN;
                languageAbbrev = "de";
                break;
            case SWISS_GERMAN:
                currentLanguage = SWISS_GERMAN;
                languageAbbrev = "de_CH";
            case ENGLISH:
                currentLanguage = ENGLISH;
                languageAbbrev = "en";
                break;
            default:
                currentLanguage = GERMAN;
                languageAbbrev = "de";
                break;
        }
        InputStream in;
        if (languageAbbrev == null) {
            in = getClass().getResourceAsStream(PATH + SUFFIX);
        }
        else {
            // locale properties
            in = getClass().getResourceAsStream(PATH + "_" + languageAbbrev + SUFFIX);
        }
        try {
            translations.load(in);
        } catch (IOException e) {
            logger.warning("Could not load translation for" + language.name().toLowerCase());
        }

        logger.info("Loaded resources for " + language.name().toLowerCase());
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
