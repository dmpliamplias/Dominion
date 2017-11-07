package com.weddingcrashers.service;

/**
 * @author dmpliamplias
 * @version $Id: xxx$
 */
public enum Language {

    // ---- Statics

    /** German. */
    GERMAN(1, "de"),
    /** Swiss German */
    SWISS_GERMAN(2, "de_CH"),
    /** English */
    ENGLISH(3, "en");


    // ---- Members

    /** The language value. */
    private final int value;
    /** The language code. */
    private final String code;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param value the value.
     * @param code the code.
     */
    Language(int value, String code) {
        this.value = value;
        this.code = code;
    }


    // ---- Methods

    public int getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

}
