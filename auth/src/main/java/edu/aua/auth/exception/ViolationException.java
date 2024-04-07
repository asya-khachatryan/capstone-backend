package edu.aua.auth.exception;

import edu.aua.auth.validation.ValidationError;

public class ViolationException extends RuntimeException {

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
