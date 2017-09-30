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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_ID", nullable = false)
    @ManyToOne
    private User user;

    @Column(name = "DATE_OF_HIGHSCORE", nullable = false)
    private LocalDateTime dateOfHighscore;

    @Column(name = "POINTS", nullable = false)
    private int points;

    @Column(name = "DURATION_FOR_HIGHSCORE", nullable = false)
    private int durationForHighscore;


    // ---- Methods

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User userid) {
        this.user = userid;
    }

    public LocalDateTime getDateOfHighscore() {
        return dateOfHighscore;
    }

    public void setDateOfHighscore(final LocalDateTime dateOfHighscore) {
        this.dateOfHighscore = dateOfHighscore;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }

    public int getDurationForHighscore() {
        return durationForHighscore;
    }

    public void setDurationForHighscore(final int durationForHighscore) {
        this.durationForHighscore = durationForHighscore;
    }

}
