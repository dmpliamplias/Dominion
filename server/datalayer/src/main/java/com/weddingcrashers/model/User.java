package com.weddingcrashers.model;

import javax.persistence.*;

/**
 * User entity.
 *
 * @author dmpliamplias
 */
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    // ---- Members

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "PASSWORD", nullable = false)
    private String password;


    // ---- Methods

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
