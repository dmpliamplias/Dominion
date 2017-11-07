package com.weddingcrashers.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

import static com.weddingcrashers.service.ServiceLocator.getLogger;
import static java.nio.charset.Charset.forName;

/**
 * The translator.
 *
 * @author dmpliamplias
 */
public class Translator {

    // ---- Statics

    /** The suffix for the translation files. */
    private static final String SUFFIX = ".properties";

    /** The path to translation resources. */
    private static final String PATH = "/translation/trans";

    /** The logger. */
    private static final Logger logger = getLogger();


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
        this.currentLanguage = language;

        translations = new Properties();
        final InputStream in = getClass().getResourceAsStream(PATH + "_" + language.getCode() + SUFFIX);
        try {
            translations.load(new InputStreamReader(in, forName("UTF-8")));
        }
        catch (IOException e) {
            logger.warning("Could not load translation for" + language.name().toLowerCase());
        }
        logger.info("Loaded resources for " + language.name().toLowerCase());
    }


    // ---- Methods

    /**
     * Returns the value for the given key from the translation properties file.
     *
     * @param key the key to load the value for.
     * @return the value for the given key.
     */
    public String getString(String key) {
        final String value = translations.getProperty(key);
        if (value == null) {
            logger.warning("No value defined for key: " + key);
            return "UNDEFINED";
        }
        else {
            return value;
        }
    }

    public Language getCurrentLanguage() {
        return currentLanguage;
    }

}
