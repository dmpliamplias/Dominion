package com.weddingcrashers.validation;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.UserService;
import com.weddingcrashers.service.UserServiceImpl;
import org.apache.commons.validator.routines.EmailValidator;

import static com.weddingcrashers.validation.ValidationResult.EMAIL_ALREADY_EXISTS;
import static com.weddingcrashers.validation.ValidationResult.EMAIL_IS_INVALID;
import static com.weddingcrashers.validation.ValidationResult.OK;

public class UserValidation {

    // ---- Members

    /** The user service. */
    private static final UserService userService = new UserServiceImpl();

    /** The user. */
    private final String email;

    /** The validation result. */
    private ValidationResult validationResult = OK;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param email the email.
     */
    public UserValidation(String email) {
        this.email = email;
    }

    // ---- Methods

    /**
     * Validates the user.
     *
     * @return the validation result.
     */
    public ValidationResult validate() {
        final boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            return EMAIL_IS_INVALID;
        }
        final User userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null) {
            validationResult = EMAIL_ALREADY_EXISTS;
        }
        return validationResult;
    }

}
