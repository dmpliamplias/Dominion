package com.weddingcrashers.model;

/**
 * Settings model class.
 *
 * @author dmpliamplias
 */
public class Settings {

    // ---- Members

    /** Sound is on indicator. */
    private boolean sound;



    // ---- Methods

    public boolean isSoundOn() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

}
