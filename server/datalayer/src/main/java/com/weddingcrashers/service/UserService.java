package com.weddingcrashers.service;

import com.weddingcrashers.model.User;

import java.util.List;

/**
 * Service for creating, updating and deleting users.
 *
 * @author dmpliamplias
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to create.
     */
    User create(User user);

    /**
     * Updates the given user.
     *
     * @param user the user to update.
     */
    User update(User user);

    /**
     * Deletes the given user.
     *
     * @param user the user to delete.
     * @return {@code true} if the user was deleted, {@code false} otherwise.
     */
    boolean delete(User user);

    /**
     * Returns the user for the given email address.
     *
     * @param email the email address.
     * @return the user for the given email address.
     */
    User getUserByEmail(String email);

    /**
     * Gets the password for the given user.
     *
     * @param user the user.
     * @return the password.
     */
    String getPasswordFor(User user);

    /**
     * Lists all users.
     *
     * @return a list with all users.
     */
    List<User> list();

}
