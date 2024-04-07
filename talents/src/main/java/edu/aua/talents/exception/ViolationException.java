package edu.aua.talents.exception;

import edu.aua.talents.validation.ValidationError;

public class ViolationException extends RuntimeException{

    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
