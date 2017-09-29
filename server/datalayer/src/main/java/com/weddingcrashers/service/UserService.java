package com.weddingcrashers.service;

import com.weddingcrashers.model.User;

/**
 * Service for creating & updating users.
 *
 * @author dmpliamplias
 */
public interface UserService {

    /**
     * Returns the user for the given email address.
     *
     * @param email the email address.
     * @return the user for the given email address.
     */
    public User getUserByEmail(String email);

}
