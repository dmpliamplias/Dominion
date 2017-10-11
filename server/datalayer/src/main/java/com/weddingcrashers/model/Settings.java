package com.weddingcrashers.model;

import javax.persistence.*;

/**
 * Settings entity.
 *
 * @author dmpliamplias
 */
@Entity
@Table(name = "SETTINGS")
public class Settings extends BaseEntity {

    // ---- Members

    @OneToOne
    private User user;

    @Column(name = "PRIVATE_SETTINGS", nullable = false)
    private String settings;

    /** Sound is on indicator. */
    private boolean sound;


    // ---- Fluent API

    public Settings user(User user) {
        this.setUser(user);
        return this;
    }

    public Settings settings(String settings) {
        this.setSettings(settings);
        return this;
    }


    // ---- Methods

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(final String settings) {
        this.settings = settings;
    }

    public boolean isSoundOn() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

}
