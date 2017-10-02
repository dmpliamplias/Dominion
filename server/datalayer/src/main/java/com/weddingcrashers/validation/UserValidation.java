package com.weddingcrashers.validation;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.UserService;
import com.weddingcrashers.service.UserServiceImpl;

import static com.weddingcrashers.validation.ValidationResult.EMAIL_ALREADY_EXISTS;
import static com.weddingcrashers.validation.ValidationResult.OK;

public class UserValidation {

    // ---- Members

    /** The user service. */
    private static final UserService userService = new UserServiceImpl();

    /** The user. */
    private final User user;

    /** The validation result. */
    private ValidationResult validationResult = OK;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public UserValidation(User user) {
        this.user = user;
    }

    // ---- Methods

    /**
     * Validates the user.
     *
     * @return the validation result.
     */
    public ValidationResult validate() {
        final User userByEmail = userService.getUserByEmail(user.getUserEmail());
        if (userByEmail != null) {
            validationResult = EMAIL_ALREADY_EXISTS;
        }
        return validationResult;
    }

}
