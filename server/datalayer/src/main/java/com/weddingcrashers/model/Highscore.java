package com.weddingcrashers.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "USER_ID")
    private long userid;

    @Column(name = "DATE_OF_HIGHSCORE")
    private Date dateOfHighscore;

    @Column(name = "POINTS")
    private int points;

    @Column(name = "DURATION_FOR_HIGHSCORE")
    private int durationForHighscore;


    // ---- Methods

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(final long userid) {
        this.userid = userid;
    }

    public Date getDateOfHighscore() {
        return dateOfHighscore;
    }

    public void setDateOfHighscore(final Date dateOfHighscore) {
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
