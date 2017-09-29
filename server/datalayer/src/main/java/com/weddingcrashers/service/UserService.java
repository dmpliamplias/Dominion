package com.weddingcrashers.service;

import com.weddingcrashers.model.User;

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
    public void create(User user);

    /**
     * Returns the user for the given email address.
     *
     * @param email the email address.
     * @return the user for the given email address.
     */
    public User getUserByEmail(String email);

}
