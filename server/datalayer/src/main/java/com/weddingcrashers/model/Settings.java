package com.weddingcrashers.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Settings entity.
 *
 * @author dmpliamplias
 */
@Entity
@Table(name = "SETTINGS")
public class Settings implements Serializable {

    // ---- Members

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_ID")
    private long user;

    @Column(name = "PRIVATE_SETTINGS")
    private String settings;

    /** Sound is on indicator. */
    private boolean sound;


    // ---- Methods

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(final long user) {
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
