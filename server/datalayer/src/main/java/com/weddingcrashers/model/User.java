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

    @Column(name = "NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "IS_BLOCKED", nullable = false)
    private boolean isBlocked;

    @Column(name = "IS_SUPER_USER", nullable = false)
    private boolean isSuperUser;


    // ---- Fluent API

    public User name(String name) {
        this.setUserName(name);
        return this;
    }

    public User email(String email) {
        this.setUserEmail(email);
        return this;
    }

    public User password(String password) {
        this.setPassword(password);
        return this;
    }

    public User blocked(boolean blocked) {
        this.setBlocked(blocked);
        return this;
    }

    public User superUser(boolean superUser) {
        this.setSuperUser(superUser);
        return this;
    }


    // ---- Methods

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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

}
