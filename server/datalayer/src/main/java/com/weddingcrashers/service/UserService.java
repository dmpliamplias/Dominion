package com.weddingcrashers.service;

import com.weddingcrashers.model.User;

import java.util.List;

/**
 * Service for creating & updating users.
 *
 * @author dmpliamplias
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to create.
     */
    void create(User user);

    /**
     * Updates the given user.
     *
     * @param user the user to update.
     */
    void update(User user);

    /**
     * Returns the user for the given email address.
     *
     * @param email the email address.
     * @return the user for the given email address.
     */
    User getUserByEmail(String email);

    List<User> list();

}
