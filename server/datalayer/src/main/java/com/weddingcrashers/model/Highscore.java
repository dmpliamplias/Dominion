package com.weddingcrashers.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Highscore entity.
 *
 * @author dmpliamplias
 */
@Entity
@Table(name = "HIGHSCORE")
public class Highscore extends BaseEntity {

    // ---- Members

    @ManyToOne
    private User user;

    @Column(name = "DATE_OF_HIGHSCORE", nullable = false)
    private LocalDateTime dateOfHighscore;

    @Column(name = "POINTS", nullable = false)
    private int points;

    @Column(name = "DURATION_FOR_HIGHSCORE", nullable = false)
    private int durationForHighscore;


    // ---- Fluent API

    public Highscore user(User user) {
        this.setUser(user);
        return this;
    }

    public Highscore dateOfHighscore(LocalDateTime dateOfHighscore) {
        this.setDateOfHighscore(dateOfHighscore);
        return this;
    }

    public Highscore points(int points) {
        this.setPoints(points);
        return this;
    }

    public Highscore durationForHighscore(int durationForHighscore) {
        this.setDurationForHighscore(durationForHighscore);
        return this;
    }


    // ---- Methods

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateOfHighscore() {
        return dateOfHighscore;
    }

    public void setDateOfHighscore(LocalDateTime dateOfHighscore) {
        this.dateOfHighscore = dateOfHighscore;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDurationForHighscore() {
        return durationForHighscore;
    }

    public void setDurationForHighscore(int durationForHighscore) {
        this.durationForHighscore = durationForHighscore;
    }

}
